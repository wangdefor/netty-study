package com.wy.gobang.util;

import com.wy.gobang.msg.GoBangModelMsg;
import com.wy.gobang.util.BytesUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wangyong
 * @Classname GoBangEncoder
 * @Description GoBangEncoder MessageToByteEncoder
 * @Date 2021/5/29 16:03
 */
public class GoBangEncoder extends MessageToByteEncoder<GoBangModelMsg> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GoBangModelMsg goBangModelMsg, ByteBuf buf) throws Exception {
        byte[] bytes = BytesUtil.objectToBytes(goBangModelMsg);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }
}
