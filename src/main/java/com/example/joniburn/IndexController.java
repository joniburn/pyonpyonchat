package com.example.joniburn;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ComponentScan
@EnableAutoConfiguration
public class IndexController {

    @Autowired
    private ChatService chatService;

    @Autowired
    HttpSession session;

    @RequestMapping(value="/", produces="text/plain")
    @ResponseBody
    public String index() {
        return "Hello, Spring PYON PYON";
    }

    @RequestMapping("/chat")
    public String chatView(ModelMap model) {
        model.put("name", session.getAttribute("name"));
        return "chat";
    }

    @RequestMapping(value="/chat-api",
            method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void chat(@RequestBody Chat chat) {
        chatService.addChat(chat);

        // セッションに発言者の名前を保存
        session.setAttribute("name", chat.name);
    }

    @RequestMapping(value="/chat-api",
            method=RequestMethod.GET)
    @ResponseBody
    public List<Chat> getAllChat() {
        return chatService.getAllChat();
    }

    public static void main(String[] args) {
        SpringApplication.run(IndexController.class, args);
    }
}
