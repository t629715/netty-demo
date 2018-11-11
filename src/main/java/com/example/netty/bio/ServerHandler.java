package com.example.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    private Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression = null;
            String result;
            while (true){
                if ((expression = in.readLine()) == null){
                    break;
                }
                logger.info("服务端收到信息：{}",expression);
                result = Calcutor.cal(expression);
                out.println(result);

            }
        }catch (Exception e){
            e.printStackTrace();
            this.logger.error(e.getLocalizedMessage());
        }finally {
            if (in != null) {
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
                    socket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}
