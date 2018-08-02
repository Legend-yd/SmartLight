package yd.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: yd
 * @Date: 2018/7/12 14:28
 * @Version 1.0
 */
public class MySocket extends Thread {

    @Override
    public void run() {

        ServerSocket serversocket = null;
        /*try {
            serversocket = new ServerSocket(12333);

            for (; ; ) {
                Socket socket = serversocket.accept();
                System.out.println("连接成功");
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                System.out.println(socket.getInetAddress().getHostName());
                SocketManage.getManage().add(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}
