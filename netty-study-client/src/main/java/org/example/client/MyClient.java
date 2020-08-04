package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import org.example.MyServerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName MyClient
 * @Description client
 * @Date 2020/5/26 10:10
 * @Author wangyong
 * @Version 1.0
 **/
public class MyClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitialize());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channelFuture.channel().writeAndFlush(br.readLine() + "\n\r");
                Channel read = channelFuture.channel();
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    private static class MyClientInitialize extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline
                    .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                    .addLast(new LengthFieldPrepender(4))
                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                    .addLast(new StringEncoder(CharsetUtil.UTF_8))
                    .addLast(new MyClientHandler());
        }

        private class MyClientHandler extends SimpleChannelInboundHandler<String> {


            @Override
            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                System.out.println("i has receive a msg" + msg);
                ctx.channel().writeAndFlush("login 123");
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                ctx.writeAndFlush("login 123");
            }
        }
    }
}
