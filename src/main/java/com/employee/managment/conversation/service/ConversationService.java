package com.employee.managment.conversation.service;

import com.employee.managment.conversation.common.ProfileRequest;
import com.employee.managment.conversation.record.ChatMessage;
import com.employee.managment.conversation.record.Conversation;
import com.employee.managment.conversation.repository.ConversationRepository;
import com.employee.managment.conversation.util.EmployeeMgmt;
import com.employee.managment.profiles.common.EmployeeProfileRequest;
import com.employee.managment.profiles.record.EmployeeProfile;
import com.employee.managment.profiles.repository.EmployeeProfileRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    public ConversationService(ConversationRepository conversationRepository, EmployeeProfileRepository employeeProfileRepository) {
        this.conversationRepository = conversationRepository;
        this.employeeProfileRepository = employeeProfileRepository;
    }


    public void createConversation(ProfileRequest profileRequest) {
        EmployeeProfile profile = employeeProfileRepository.findById(profileRequest.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the profile"));

        Conversation conversation = new Conversation(
                EmployeeMgmt.generateUId(),
                profile.id(),
                new ArrayList<>());
          conversationRepository.save(conversation);
    }


    public Conversation addMessagesToconversation(String conversationId, ChatMessage message) {
       Conversation conversation= conversationRepository.findById(conversationId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find ID :"+conversationId));
        employeeProfileRepository.findById(message.profileId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find ID: "+message.profileId()));
       ChatMessage chatMessage=new ChatMessage(message.textMessage(), message.profileId(),
               LocalDateTime.now());
        conversation.messages().add(chatMessage);
        return conversationRepository.save(conversation);

    }
}
