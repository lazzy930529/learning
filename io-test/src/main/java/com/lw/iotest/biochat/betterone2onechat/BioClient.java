package com.lw.iotest.biochat.betterone2onechat;

import java.io.IOException;
import java.net.Socket;

/**
 * BIO聊天，客户端
 * @author liuwei
 * @date 2020-03-26 20:48
 */
public class BioClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8888)
        ) {
            Thread readT = new ReaderThread(socket,"服务端");
            Thread writeT = new WriterThread(socket,null);
            readT.start();
            writeT.start();
            readT.join();
            writeT.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
