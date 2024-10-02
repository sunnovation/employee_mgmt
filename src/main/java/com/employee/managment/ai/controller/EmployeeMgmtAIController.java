package com.employee.managment.ai.controller;

import com.employee.managment.ai.service.EmployeeManagmentAIService;
import com.employee.managment.conversation.record.ChatMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class EmployeeMgmtAIController {

    private final EmployeeManagmentAIService employeeManagmentAIService;

    public EmployeeMgmtAIController(EmployeeManagmentAIService employeeManagmentAIService) {
        this.employeeManagmentAIService = employeeManagmentAIService;
    }
    @GetMapping("/")
    public ResponseEntity<String> defaultApiofAI(){
        return new ResponseEntity<>(employeeManagmentAIService.chatWithOpenAIModel(), HttpStatus.OK);
    }

    @GetMapping("/employeeConversation/{conversationId}")
    public ResponseEntity<HashMap<String,Object>> chatWithAI(@PathVariable String conversationId, @RequestBody ChatMessage message){
        return new ResponseEntity<HashMap<String,Object>>(employeeManagmentAIService.chatWithUserProfileWithAIAssistance(conversationId,message), HttpStatus.OK);
    }
}
