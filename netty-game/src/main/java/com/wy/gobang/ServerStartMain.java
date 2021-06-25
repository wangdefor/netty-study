package com.wy.gobang;

import com.wy.gobang.server.Server;

/**
 * @author wangyong
 * @Classname ServerStartMain
 * @Description ServerStartMain
 * @Date 2021/6/25 17:43
 */
public class ServerStartMain {

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.port = 8089;
        server.run();
    }
}
