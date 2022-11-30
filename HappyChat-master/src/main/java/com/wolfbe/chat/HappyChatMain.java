package com.wolfbe.chat;

import com.wolfbe.chat.rabbit.rabbitManager;
import com.wolfbe.chat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * WebSocket聊天室，客户端参考docs目录下的websocket.html
 * @author Andy
 * @site http://www.wolfbe.com
 * @github https://github.com/beyondfengyu
 */
public class HappyChatMain {
    private static final Logger logger = LoggerFactory.getLogger(HappyChatMain.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        final HappyChatServer server = new HappyChatServer(Constants.DEFAULT_PORT);
        server.init();
        server.start();
        // 注册进程钩子，在JVM进程关闭前释放资源
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                server.shutdown();
                logger.warn(">>>>>>>>>> jvm shutdown");
                System.exit(0);
            }
        });
    }
}
