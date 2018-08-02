package yd.socket;

import java.io.IOException;
import java.util.HashSet;

/**
 * @Author: yd
 * @Date: 2018/7/12 14:29
 * @Version 1.0
 */
public class SocketManage {
    private static final SocketManage socketManage = new SocketManage();//单例化

    private SocketManage() {
    }//私有化构造函数

    /**
     * 返回manage对象
     *
     * @return socketManage
     */
    public static SocketManage getManage() {
        return socketManage;
    }

    HashSet<ChatSocket> socketSet = new HashSet<>();
    HashSet<String> clientSet = new HashSet();

    public void add(ChatSocket s) {
        socketSet.add(s);
        add(s.hostname);
    }

    public void add(String s) {
        clientSet.add(s);
    }

    public void publish(ChatSocket cs, String data) {
        for (ChatSocket mm : socketSet) {
            try {
                if (mm != cs) {
                    mm.send(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public String  getClient() {
        StringBuffer str = new StringBuffer();
        int i=0;
        for (String m : clientSet) {
            str.append(m);
            i++;
            if(clientSet.size()!=i){
                str.append(",");
            }
        }

        return str.toString();
    }

    public void publishAll(String data) throws IOException {
        for (ChatSocket mm : socketSet) {
            mm.send(data);


        }
    }
}
