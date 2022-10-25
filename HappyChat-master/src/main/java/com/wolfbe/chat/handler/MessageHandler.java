package com.wolfbe.chat.handler;

import com.alibaba.fastjson.JSONObject;
import com.wolfbe.chat.entity.ChatGroup;
import com.wolfbe.chat.entity.UserInfo;
import com.wolfbe.chat.proto.ChatCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andy
 * @site http://www.wolfbe.com
 * @github https://github.com/beyondfengyu
 */
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame)
            throws Exception {
        JSONObject json = JSONObject.parseObject(frame.text());
        int code = json.getInteger("code");
        //当为聊天code时再发送消息,否则不用管,交给userauth即可
        if(code==ChatCode.MESS_CODE)
        {
            int group_id=json.getInteger("group_id");
            int user_id=json.getInteger("member_id");
            String user_name=json.getString("member_name");
            String message=json.getString("message");
            ChatGroup group=UserInfoManager.getGroup(group_id);
            group.broadcastMess(user_name,message,user_id);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        UserInfoManager.removeChannel(ctx.channel());
        UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT,UserInfoManager.getAuthUserCount());
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("connection error and close the channel", cause);
        UserInfoManager.removeChannel(ctx.channel());
        UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT, UserInfoManager.getAuthUserCount());
    }

}
