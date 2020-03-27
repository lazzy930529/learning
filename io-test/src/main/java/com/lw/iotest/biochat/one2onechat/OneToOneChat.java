package com.lw.iotest.biochat.one2onechat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuwei
 * @date 2020-03-27 10:02
 */
public class OneToOneChat {
    private Scanner scanner = new Scanner(System.in);
    private static final String BYE = "bye";
    private String from;
    private String greeting;
    private Socket socket;

    public OneToOneChat(String from, Socket socket, String greeting) {
        this.from = from;
        this.greeting = greeting;
        this.socket = socket;
    }

    public void chatting() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            if (null != greeting) {
                writer.println(greeting);
                writer.flush();
            }
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.equalsIgnoreCase(BYE)) {
                    System.out.println(s);
                    System.out.println(from + "断开连接");
                    break;
                }
                System.out.println(from + ":" + s);
                String words = scanner.nextLine();
                writer.println(words);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
