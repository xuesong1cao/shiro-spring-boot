package com.controller;

import com.constant.WebsocketConstant;
import com.entity.WiselyMessage;
import com.entity.WiselyResponse;
import com.service.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Lists;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WsController {

    @Resource
    WebSocketService webSocketService;

    @MessageMapping(WebsocketConstant.FORETOSERVERPATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
//    @SendTo("/topic/getResponse")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public void say(WiselyMessage message) throws Exception {
        List<String> users = new ArrayList<>();
        users.add("d892bf12bf7d11e793b69c5c8e6f60fb");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.send2Users(users, new WiselyResponse("admin hello"));
        WiselyResponse msg = new WiselyResponse();
        msg.setResponseMessage("Welcome, " + message.getName() + "!嘿嘿");
        webSocketService.sendMsg(msg);

//        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }


    @RequestMapping("/websocket")
    public String websocket(ModelMap modelMap){
        List<String> users = new ArrayList<>();
        users.add("d892bf12bf7d11e793b69c5c8e6f60fb");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        modelMap.put("users",users);
        return "websocket";
    }

    @RequestMapping("/websocketdemo")
    public String websocketdemo(){
        WiselyResponse msg = new WiselyResponse();
        msg.setResponseMessage("Welcome, 通来!嘿嘿");
        webSocketService.sendMsg(msg);
        return "success";
    }
}
