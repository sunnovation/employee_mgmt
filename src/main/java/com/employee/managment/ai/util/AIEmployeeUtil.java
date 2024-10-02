package com.employee.managment.ai.util;

import java.util.UUID;

public class AIEmployeeUtil {

    public String generateAIEmployeeResponseId(){
        return UUID.randomUUID().toString();
    }
}
