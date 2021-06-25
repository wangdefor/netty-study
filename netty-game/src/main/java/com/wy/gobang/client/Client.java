package com.wy.gobang.client;

import cn.hutool.core.lang.Assert;
import com.wy.gobang.util.GoBangDecoder;
import com.wy.gobang.util.GoBangEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangyong
 * @Classname NettyClient
 * @Description NettyClient
 * @Date 2021/6/25 10:41
 */
public class Client {

    private static Client client = null;

    private ChannelFuture future;

    public ChannelFuture getFuture(){
        Assert.isTrue(future != null);
        return future;
    }

    public static Client getInstance(){
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public void run(String host,Integer port) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new GoBangDecoder())
                            .addLast(new GoBangEncoder())
                            .addLast(new GoBangClientHandler());
                }
            });
            future = b.connect(host, port).sync();
            future.channel().closeFuture().sync();
            future.channel().writeAndFlush(12);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
