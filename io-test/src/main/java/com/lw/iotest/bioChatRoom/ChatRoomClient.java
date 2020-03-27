package com.lw.iotest.bioChatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuwei
 * @date 2020-03-27 15:05
 */
public class ChatRoomClient {

    public static void main(String[] args) {
        try (
                Socket s = new Socket("localhost", 10001);
        ) {
            Thread rThread = new Thread(new ReaderTask(s));
            Thread wThread = new Thread(new WriterTask(s));
            rThread.start();
            wThread.start();
            rThread.join();
            wThread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class ReaderTask implements Runnable {
        private Socket socket;

        ReaderTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String words;
                while ((words = br.readLine()) != null) {
                    System.out.println(words);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class WriterTask implements Runnable {
        private Socket socket;

        WriterTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    Scanner sc = new Scanner(System.in);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                while (true) {
                    String words = sc.nextLine();
                    pw.println(words);
                    pw.flush();
                    if ("bye".equalsIgnoreCase(words)) {
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
