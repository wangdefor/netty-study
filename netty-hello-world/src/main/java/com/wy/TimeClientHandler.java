package com.wy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyong
 * @Classname TimeClientHandler
 * @Description TODO
 * @Date 2021/4/30 14:15
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime m = (UnixTime) msg;
        System.out.println("接受到服务端数据：" + m.toString());
        TimeUnit.SECONDS.sleep(2);
        ctx.writeAndFlush(new UnixTime());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UnixTime time = new UnixTime();
        ctx.writeAndFlush(time);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
