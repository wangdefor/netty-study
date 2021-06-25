package com.wy.gobang.util;

import com.wy.gobang.util.BytesUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wangyong
 * @Classname GoBangDecoder
 * @Description GoBangDecoder
 * @Date 2021/5/29 16:04
 */
@Slf4j
public class GoBangDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        if(in.readableBytes() < 8) return;
        in.markReaderIndex();

        int length = in.readInt();
        int readableBytes = in.readableBytes();
        log.info("length {},readable {}",length,readableBytes);
        if(readableBytes < length) {
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[readableBytes];
        in.readBytes(bytes);
        Object object = BytesUtil.bytesToObject(bytes);
        list.add(object);
    }
}
