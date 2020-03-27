package com.lw.iotest.biochat.one2onechat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO聊天，服务器端
 *
 * @author liuwei
 * @date 2020-03-26 20:48
 */
public class BioServer {

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket socket = serverSocket.accept();
        ) {
            OneToOneChat oneToOneChat = new OneToOneChat("客户端", socket, "你好客户端");
            oneToOneChat.chatting();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
