package com.service;

import com.constant.WebsocketConstant;
import com.entity.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户,发送给监听的用户
     *
     * @param msg
     */
    public void sendMsg(WiselyResponse msg) {
        template.convertAndSend(WebsocketConstant.PRODUCERPATH, msg);
    }

    /**
     * 发送给指定用户
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, WiselyResponse msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, WebsocketConstant.P2PPUSHPATH, msg);
        });
    }
}
