package com.wy.gobang.client;

import cn.hutool.json.JSONUtil;
import com.wy.gobang.frame.GoBangFrame;
import com.wy.gobang.msg.GoBangModelMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyong
 * @Classname TimeClientHandler
 * @Description GoBangClientHandler
 * @Date 2021/4/30 14:15
 */
@Slf4j
public class GoBangClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        GoBangModelMsg m = (GoBangModelMsg) msg;
        GoBangFrame frame = GoBangFrame.getInstance();
        int x = (m.getX() - 25) / 50;
        int y = (m.getY() - 50) / 50;
        GoBangModelMsg[][] coordinate = GoBangFrame.coordinate;
        coordinate[x][y] = m;
        GoBangFrame.QiPanJPanel canvas = frame.getCanvas();
        //重新绘制棋子
        canvas.repaint();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       super.channelActive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
