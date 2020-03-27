package com.lw.iotest.biochat.betterone2onechat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO聊天，服务器端
 * @author liuwei
 * @date 2020-03-26 20:48
 */
public class BioServer {

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket socket = serverSocket.accept();
        ) {
            Thread readT = new ReaderThread(socket,"客户端");
            Thread writeT = new WriterThread(socket,"你好客户端");
            readT.start();
            writeT.start();
            readT.join();
            writeT.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
