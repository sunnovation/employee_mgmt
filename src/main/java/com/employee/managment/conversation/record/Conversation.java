package com.employee.managment.conversation.record;


import org.springframework.context.annotation.Profile;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessage> messages


) {
}
