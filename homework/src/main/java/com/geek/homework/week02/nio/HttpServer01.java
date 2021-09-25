package com.geek.homework.week02.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket accept = serverSocket.accept();
            service(accept);
        }
    }

    private static void service(Socket accept) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(accept.getOutputStream(), true);

            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hello,nio1";
            printWriter.println("Content-Length: " + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            accept.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
