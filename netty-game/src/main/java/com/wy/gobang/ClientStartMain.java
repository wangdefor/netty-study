package com.wy.gobang;

import com.wy.gobang.client.Client;
import com.wy.gobang.frame.GoBangFrame;
import com.wy.gobang.msg.User;

import java.awt.*;

/**
 * @author wangyong
 * @Classname ClientStartMain
 * @Description ClientStartMain
 * @Date 2021/6/25 17:43
 */
public class ClientStartMain {

    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        if(args.length != 0){
            host = args[0];
        }
        GoBangFrame frame = GoBangFrame.getInstance();
        frame.setVisible(Boolean.TRUE);
        //设置用户
        User user = new User("1", Color.GREEN);
        frame.setUser(user);

        //启动客户端
        Client client = Client.getInstance();
        int port = 8089;
        client.run(host,port);
    }
}
