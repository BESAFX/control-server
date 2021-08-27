package com.besafx.app.ws;

import com.besafx.app.model.MessageRequest;
import com.besafx.app.model.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageResponse greet(MessageRequest request) {
        return new MessageResponse(request.getFrom(), request.getText(), new Date().toString());
    }
}
