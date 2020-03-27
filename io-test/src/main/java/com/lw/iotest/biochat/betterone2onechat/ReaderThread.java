package com.lw.iotest.biochat.betterone2onechat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author liuwei
 * @date 2020-03-27 11:38
 */
public class ReaderThread extends Thread {
    private Socket socket;
    private String from;

    ReaderThread(Socket socket, String from) {
        this.socket = socket;
        this.from = from;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String s;
            while (true) {
                try {
                    s = reader.readLine();
                    if (s == null) {//socket断开连接后，read结束阻塞返回null
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("catch e:" + e.getClass().getSimpleName());
                    System.out.println("stop thread and leave");
                    break;
                }
                System.out.println(from + ": " + s);
                if (s.equalsIgnoreCase("bye")) {
                    System.out.println(from + "离开聊天");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
