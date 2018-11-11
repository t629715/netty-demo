package com.example.netty.bio;

import java.io.IOException;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        Thread.sleep(1000);

        char[] op = {'+','-','*','/'};
        final Random random = new Random(System.currentTimeMillis());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String expression = random.nextInt(10)+op[random.nextInt(4)]+random.nextInt(10)+"";
                    Client.send(expression);
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
