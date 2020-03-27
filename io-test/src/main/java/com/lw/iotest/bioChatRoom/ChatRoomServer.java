package com.lw.iotest.bioChatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuwei
 * @date 2020-03-27 15:05
 */
public class ChatRoomServer {

    public static void main(String[] args) {
        try (
                ServerSocket ss = new ServerSocket(10001);
        ) {
            while (true) {
                Socket s = ss.accept();
                enterChatRoom(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enterChatRoom(Socket s) {
        new Thread(() -> {
            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                pw.println("请输入用户名：");
                pw.flush();
                String userName = br.readLine();
                Room.addUser(s, userName);
                String words;
                while ((words = br.readLine()) != null) {
                    Room.sendMsg(words, s);
                }
                Room.removeUser(s);
            } catch (IOException e) {
                e.printStackTrace();
                Room.removeUser(s);
            }

        }).start();
    }
}
