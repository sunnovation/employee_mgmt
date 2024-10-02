package com.employee.managment.ai.service;

import com.employee.managment.conversation.record.ChatMessage;
import com.employee.managment.conversation.record.Conversation;
import com.employee.managment.conversation.repository.ConversationRepository;
import com.employee.managment.profiles.record.EmployeeProfile;
import com.employee.managment.profiles.record.UserProfile;
import com.employee.managment.profiles.repository.EmployeeProfileRepository;
import com.employee.managment.profiles.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeManagmentAIService {

    private final OpenAiChatModel chatClient;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final ConversationRepository conversationRepository;
    private final UserProfileRepository userProfileRepository;
    @Autowired
    private LLMChatResponseProcess chatResponseProcess;

    public EmployeeManagmentAIService(OpenAiChatModel chatClient, EmployeeProfileRepository employeeProfileRepository, ConversationRepository conversationRepository, UserProfileRepository userProfileRepository) {
        this.chatClient = chatClient;
        this.employeeProfileRepository = employeeProfileRepository;
        this.conversationRepository = conversationRepository;
        this.userProfileRepository = userProfileRepository;
    }

    String name = "Pawan Barthunia";
    int age = 30;
    String promptText = """
    Tell about {name} to a  software developer in synechrone and As of now {age} years old of  person.
    """;

    //Get from user



    public String chatWithOpenAIModel(){
        PromptTemplate promptTemplate = new PromptTemplate(promptText);
        Message message = promptTemplate.createMessage(Map.of("name", name, "age", age));

        Prompt prompt = new Prompt(List.of(message));
        Generation generation = chatClient.call(prompt).getResult();
        log.info(generation.getOutput().getContent());
        return generation.getOutput().getContent();
    }


    public HashMap<String,Object> chatWithUserProfileWithAIAssistance(String conversationId, ChatMessage message){
        HashMap<String,Object> response=new HashMap<>();
        Conversation conversation=conversationRepository.findById(conversationId).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find ID:"+conversationId));
        String matchProfileId=conversation.profileId();
        EmployeeProfile profile = employeeProfileRepository.findById(matchProfileId)
                . orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find ID:"+matchProfileId));
        String id=message.profileId();
        System.out.println("Message User ID"+message.profileId());
        UserProfile user=userProfileRepository.findById(id). orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Unable to find ID:"+message.profileId()));
        ChatMessage chatMessag1=new ChatMessage(
                message.textMessage(),
                message.profileId(),
                LocalDateTime.now()
        );
        conversation.messages().add(chatMessag1);
       chatResponseProcess.processAIChatResponseRequest(conversation,profile,user,chatClient);

        response.put("conversation",conversationRepository.save(conversation));

        return response;

    }


}
