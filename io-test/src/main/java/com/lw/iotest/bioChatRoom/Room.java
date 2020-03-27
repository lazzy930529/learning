package com.lw.iotest.bioChatRoom;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author liuwei
 * @date 2020-03-27 15:54
 */
public class Room {
    private static ConcurrentHashMap<Socket, String> users = new ConcurrentHashMap<>();

    public static void broadcast(String s) {
        doBroadcast(s, users.keySet());
    }

    public static void sendMsg(String s, Socket from) {
        String words = users.get(from) + ":" + s;
        doBroadcast(words, users.keySet().stream().filter(socket -> !socket.equals(from)).collect(Collectors.toSet()));
    }

    private static void doBroadcast(String s, Set<Socket> users) {
        users.forEach(socket -> {
            try {//注意：此处不能使用try-with-resource语法糖，因为输入输出流的关闭会导致socket的关闭
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                pw.println(s);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void addUser(Socket socket, String userName) {
        users.put(socket, userName);
        broadcast(userName + "加入了聊天室");
    }

    public static void removeUser(Socket socket) {
        sendMsg(users.get(socket) + "离开了聊天室", socket);
        users.remove(socket);
    }

    public static String getUserName(Socket socket) {
        return users.get(socket);
    }
}
