package com.wy.gobang.server;

import cn.hutool.json.JSONUtil;
import com.wy.gobang.msg.GoBangModelMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wangyong
 * @Classname GoBangServerHandler
 * @Description GoBangServerHandler
 * @Date 2021/5/29 16:06
 */
@Slf4j
public class GoBangServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelRead(ChannelHandlerContext ctx, final Object msg) throws Exception {
        if(msg instanceof GoBangModelMsg){
            log.info("当前五子棋的下子坐标为 {}", JSONUtil.toJsonStr(msg));
            //开始通知对应的客户端
            channelGroup.forEach(channel -> {
                log.info("开始通知所有连接过来的通道");
                channel.writeAndFlush(msg);
            });
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info(ctx.name() + "注册进来了");
        ChannelId id = ctx.channel().id();
        //加入channel组
        channelGroup.add(ctx.channel());
        channelGroup.find(id);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
}
