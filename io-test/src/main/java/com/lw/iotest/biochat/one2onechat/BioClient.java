package com.lw.iotest.biochat.one2onechat;

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
            OneToOneChat oneToOneChat = new OneToOneChat("服务器", socket, null);
            oneToOneChat.chatting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
