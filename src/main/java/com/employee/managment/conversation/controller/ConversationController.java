package com.employee.managment.conversation.controller;

import com.employee.managment.conversation.common.ProfileRequest;
import com.employee.managment.conversation.record.ChatMessage;
import com.employee.managment.conversation.record.Conversation;
import com.employee.managment.conversation.service.ConversationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ConversationController {


    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/conversations")
    public ResponseEntity<Void> createConversation(@RequestBody ProfileRequest profileRequest){
        conversationService.createConversation(profileRequest);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/conversations/{conversationId}")
    public ResponseEntity<Conversation> addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessage message){
        log.info("ConversationController::addMessageToConversation{}",conversationId);
        return new ResponseEntity<>(conversationService.addMessagesToconversation(conversationId,message),HttpStatus.CREATED);
    }

}
