package com.lw.iotest.biochat.betterone2onechat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuwei
 * @date 2020-03-27 11:39
 */
public class WriterThread extends Thread {
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private String greeting;

    WriterThread(Socket socket, String greeting) {
        this.socket = socket;
        this.greeting = greeting;
    }

    @Override
    public void run() {
        try (
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            if (greeting != null) {
                writer.println(greeting);
                writer.flush();
            }
            String words;
            while ((words = scanner.nextLine()) != null) {
                writer.println(words);
                writer.flush();
                if(words.equalsIgnoreCase("bye")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
