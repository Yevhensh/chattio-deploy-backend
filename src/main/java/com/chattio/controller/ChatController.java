package com.chattio.controller;

import com.chattio.dto.ClientMessage;
import com.chattio.dto.MessageDto;
import com.chattio.entity.Message;
import com.chattio.service.DialogService;
import com.chattio.util.helper.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DialogService dialogService;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @MessageMapping("/chat/{dialogId}")
    @SendTo("/topic/{dialogId}/messages")
    public MessageDto handle(@RequestBody ClientMessage clientMessage, @DestinationVariable Long dialogId) {
        return dialogService.sendMessage(clientMessage.getMessage(), dialogId, clientMessage.getUsername());
    }
}
