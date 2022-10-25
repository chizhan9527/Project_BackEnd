package com.backend.videoproject_backend.utils.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.springframework.stereotype.Component;

/**
 * Socket 初始化器，每一个Channel进来都会调用这里的 InitChannel 方法
 **/
@Component
public class SocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 添加对byte数组的编解码，netty提供了很多编解码器，可以根据需要选择
        pipeline.addLast(new ByteArrayDecoder());
        pipeline.addLast(new ByteArrayEncoder());
        // 添加上自己的处理器
        pipeline.addLast(new SocketHandler());
    }
}

