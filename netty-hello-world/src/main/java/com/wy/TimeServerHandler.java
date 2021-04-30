package com.wy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyong
 * @Classname TimeHandler
 * @Description TimeHandler
 * @Date 2021/4/30 14:12
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        UnixTime time = new UnixTime();
        ctx.writeAndFlush(time);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime time = (UnixTime) msg;
        System.out.println("接受到客户端数据：" + time.toString());
        TimeUnit.SECONDS.sleep(2);
        ctx.writeAndFlush(new UnixTime());
    }
}
