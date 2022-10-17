package com.backend.videoproject_backend.utils.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket拦截器，用于处理客户端的行为
 **/
@Slf4j
public class SocketHandler extends ChannelInboundHandlerAdapter {
    public static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 读取到客户端发来的消息
     *
     * @param ctx ChannelHandlerContext
     * @param msg msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 由于配置的是 字节数组 编解码器，所以这里取到的用户发来的数据是 byte数组
        byte[] data = (byte[]) msg;
        log.info("收到消息: " + new String(data));
        // 给其他人转发消息
        for (Channel client : clients) {
            if (!client.equals(ctx.channel())) {
                client.writeAndFlush(data);
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("新的客户端链接：" + ctx.channel().id().asShortText());
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        clients.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        cause.printStackTrace();
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}

