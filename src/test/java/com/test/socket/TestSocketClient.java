package com.test.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * 客户端结束服务器响应
 *
 * @author Nick
 */
@Slf4j
public class TestSocketClient {


    public static String sendSynMsg(String ip, String port, String xml, int timeout) throws Exception {
        //为了简单起见，所有的异常都直接往外抛  
        //与服务端建立连接  
        Socket client = new Socket(ip, Integer.parseInt(port));
        //建立连接后就可以往服务端写数据了  
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        writer.write("Hello Server.");
        writer.write("eof123");
        writer.flush();

        String reslult = readFromServer(client);

        writer.close();

        client.close();
        return reslult;
    }

    //一次性读取响应数据,对数据传输量小的可使用
    private static String readFromServer(Socket client) throws UnsupportedEncodingException, IOException {
        //读取服务器端响应  
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "GBK"));
        char chars[] = new char[1024];
        StringBuffer sb = new StringBuffer();
        sb.append(new String(chars, 0, reader.read(chars)));
        reader.close();
        log.info("from server: " + sb);
        return sb.toString();
    }

    //数据传输量较大,或考虑到网络延迟,一次性读取会发生读取数据不完整的问题。
    //可以通过服务端和客户端约定结束标识。来作为break的条件。
    private static String readFromServer2(Socket client) throws UnsupportedEncodingException, IOException {


        return null;
    }

    public static void main(String[] args) throws Exception {
        String s1 = sendSynMsg("172.56.3.86", "8888", "12222222", 10000);

        log.info(s1);
    }
}
