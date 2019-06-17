package com.waffle.integrated.frame.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * netty server demo
 *
 * @author yixiaoshuang
 * @date 2019-06-07 19:06
 */
@Slf4j
public class EchoServer {

    private static final boolean SSL = System.getProperty("ssl") != null;
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    private static final String WEBSOCKET_PATH = "/ws";

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        //监听连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).
                    //注册NioServerSocketChannel
                            channel(NioServerSocketChannel.class).
                    //注册Socket处理器
                            childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //用于Http请求的编码或者解码
                            pipeline.addLast("http-codec", new HttpServerCodec());
                            //把Http消息组成完整地HTTP消息
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                            //向客户端发送HTML5文件
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());

                            pipeline.addLast(new LoggingHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
                            //实际处理的Handler
                            pipeline.addLast("handler", new EchoServerHandler());
                        }
                    });

            ChannelFuture f = bootstrap.bind(PORT).sync();

            f.addListener(future -> {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
            });

            f.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            log.error("ex:{}", e);
        } finally {
            //优雅的关闭监听线程
            bossGroup.shutdownGracefully();
            //优雅的关闭工作线程
            workerGroup.shutdownGracefully();
        }
    }
}
