package com.example.joniburn;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private static final int CHAT_MAX = 100;

    private List<Chat> chatList = new LinkedList<Chat>();

    public void addChat(Chat chat) {
        chatList.add(chat);

        // 最大個数を超えた場合、古い方から削除
        if (chatList.size() > CHAT_MAX) {
            chatList.remove(0);
        }
    }

    public List<Chat> getAllChat() {
        return Collections.unmodifiableList(chatList);
    }
}
