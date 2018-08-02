package yd.socket;

import java.io.IOException;
import java.net.Socket;

/**用于测试连接
 * @Author: yd
 * @Date: 2018/7/13 10:35
 * @Version 1.0
 */
public class my {

    public static void main(String[] args) {
        try {
            //使用内网穿透的"47.91.128.54",12977
            Socket socket = new Socket("192.168.1.7",12333);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
