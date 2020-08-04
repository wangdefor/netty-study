package org.example;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @ClassName Test
 * @Description TODO
 * @Date 2020/7/20 10:35
 * @Author wangyong
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        int max = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(max);
    }
}
