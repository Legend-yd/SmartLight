package yd.servlet;


import net.sf.json.JSONObject;
import yd.socket.ChatSocket;
import yd.socket.SocketManage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * @Author: yd
 * @Date: 2018/7/12 15:04
 * @Version 1.0
 */
public class MyServlet extends HttpServlet {
    public void init() {
        new Thread() {
            @Override
            public void run() {
                ServerSocket serversocket = null;
                try {
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
                }
            }
        }.start();

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    Socket socket;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyJsonUtil myutil = new MyJsonUtil(request, response);
        JSONObject clientToService = myutil.getInfo();
        System.out.println(clientToService);
        if (clientToService.containsKey("flashLight")) {//闪光灯
            send(clientToService.toString());
            System.out.println(clientToService);
        }
        if (clientToService.containsKey("lightLevel")) {//等级
            send(clientToService.toString());
            System.out.println(clientToService);
        }
        if (clientToService.containsKey("client")) {//获取设备
            System.out.println(clientToService);
            String client = SocketManage.getManage().getClient();
            System.out.println(client);
            JSONObject mjson = new JSONObject();
            mjson.accumulate("hostname",client);
            System.out.println(mjson);
            myutil.sendInfo(mjson);

        }
    }

    /*public void send(String data) {
        data += "\n";
        try {
            socket.getOutputStream().write(data.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void send(String data) throws IOException {
        SocketManage.getManage().publishAll(data);
    }
}
