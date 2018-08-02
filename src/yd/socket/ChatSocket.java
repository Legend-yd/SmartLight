package yd.socket;

import net.sf.json.JSONObject;
import yd.servlet.MyJsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashSet;

/**
 * @Author: yd
 * @Date: 2018/7/12 14:29
 * @Version 1.0
 */
public class ChatSocket extends Thread {

    private Socket socket;
    public String hostname;

    public ChatSocket(Socket socket) {
        this.socket = socket;
        hostname=socket.getInetAddress().getHostName();
    }

    /**
     * 发送消息
     */
    public void send(String data) throws IOException {
        data += "\n";
        socket.getOutputStream().write(data.getBytes("utf-8"));
    }

    /**
     * 接受消息
     *
     * @throws IOException
     */
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            String s = null;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                sendToClient(s);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //转发信息
    private void sendToClient(String s){
        JSONObject json = JSONObject.fromObject(s);
        if(json.containsKey("lightLevel")){
            SocketManage.getManage().publish(this,s);
        }
        if(json.containsKey("flashLight")){
            SocketManage.getManage().publish(this,s);
        }
    }
}
