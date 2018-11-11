package com.example.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    /**
     * 服务端的端口号
     */
    private static int DEFAULT_SERVER_PORT = 7777;
    /**
     * 服务端的IP
     */
    private static String DEFAULT_SERVER_IP = "127.0.0.1";


    public static void send(String expression){
        send(DEFAULT_SERVER_PORT,expression);
    }

    private static void send(int port, String expression) {
        System.out.println("算术表达式为：{}"+expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(expression);
            System.out.println("_结果为：{}"+in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null){
                out.close();
                out = null;
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
