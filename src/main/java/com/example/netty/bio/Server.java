package com.example.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端源码
 */
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    //默认端口号
    private static final int DEFAULT_PORT = 7777;
    //单例的serverSocket
    private static ServerSocket serverSocket;
    //启动的方法
    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException{
        if (serverSocket == null) return;
        try{
            serverSocket = new ServerSocket(DEFAULT_PORT);
            logger.info("服务端已启动，端口号："+DEFAULT_PORT);
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket != null){
                logger.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

}
