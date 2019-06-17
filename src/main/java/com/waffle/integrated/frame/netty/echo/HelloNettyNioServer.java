package com.waffle.integrated.frame.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty nio server demo
 *
 * @author yixiaoshuang
 * @date 2019-06-06 21:01
 */
public class HelloNettyNioServer {

    public void server(int port) throws Exception {
        //监听连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new EchoServerHandler())
                                    .addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    });
            // start the server
            ChannelFuture f = bootstrap.bind(port).sync();

            // wait until the server socket is closed
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        HelloNettyNioServer server = new HelloNettyNioServer();
        server.server(8899);
    }

}
