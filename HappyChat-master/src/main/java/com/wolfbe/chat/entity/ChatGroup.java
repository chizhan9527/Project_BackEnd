package com.wolfbe.chat.entity;

import com.wolfbe.chat.handler.UserInfoManager;
import com.wolfbe.chat.proto.ChatProto;
import com.wolfbe.chat.util.BlankUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChatGroup {
    public LinkedList<UserInfo> group_members;   //存储组内成员
    public String group_name;     //社团昵称
    public int group_id;   //社团号
    private  ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);   //用来上锁的,可能会多人同时访问

    private int online_member;
    //暂时规定必须要有一个人来建立新群
    public ChatGroup(String name, int id, int member_id, String member_name, Channel channel){
        group_id=id;
        group_name=name;
        online_member=0;
        UserInfo first=UserInfoManager.saveUser(channel,member_name,member_id);   //将新建立用户加入群组
        group_members.add(first);
    }
    public boolean add_user(UserInfo member){
        if(group_members.add(member)){
            online_member+=1;
            return true;
        }
        else
            return false;
    }
    //可能之后会根据id删除成员,先暂时不管了
    public boolean delete_user(UserInfo member){
        if(group_members.remove(member))
            return true;
        else
            return false;
    }
    //向群内所有成员广播消息
    public  void broadcastMess(String nick_name, String message,int user_id) {
            try {
                rwLock.readLock().lock();
                for(UserInfo mid:group_members){
                    Channel ch=mid.getChannel();
                    ch.writeAndFlush(new TextWebSocketFrame(ChatProto.buildMessProto(user_id,nick_name,message)));
                }
            } finally {
                rwLock.readLock().unlock();
            }
    }
    //暂时还没写
    public void ping_message(){}
    public void pong_message(){}




}
