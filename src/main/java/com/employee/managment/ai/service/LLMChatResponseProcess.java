package com.employee.managment.ai.service;

import com.employee.managment.conversation.record.ChatMessage;
import com.employee.managment.conversation.record.Conversation;
import com.employee.managment.profiles.record.EmployeeProfile;
import com.employee.managment.profiles.record.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LLMChatResponseProcess {

    public Conversation processAIChatResponseRequest(Conversation conversation, EmployeeProfile profile, UserProfile user, OpenAiChatModel chatClient) {
        String systemMessageText= """
                Welcome to employee managment system  Tell about {name} to a  software developer  As of now the person living in {age} . 
                """;
        String name=profile.empName();
        String age=profile.location();
        SystemMessage systemMessage=new SystemMessage(systemMessageText);
        String userMessageText=conversation.messages().get(0).textMessage();
        List<AbstractMessage> conversationMessages=conversation.messages().stream()
                .map(message->{
                    if(message.profileId().equalsIgnoreCase(profile.id())){
                        return new AssistantMessage(message.textMessage());
                    }else{
                        return new UserMessage(message.textMessage());
                    }
                }).toList();

        List<Message> allMessage=new ArrayList<>();
        allMessage.add(systemMessage);
        allMessage.addAll(conversationMessages);
        Prompt prompt = new Prompt(allMessage);
        Generation generation = chatClient.call(prompt).getResult();
        log.info(generation.getOutput().getContent());
        conversation.messages().add(
                new ChatMessage(
                        generation.getOutput().getContent(),
                        profile.id(),
                        LocalDateTime.now()
                )
        );
        return conversation;
    }
}
