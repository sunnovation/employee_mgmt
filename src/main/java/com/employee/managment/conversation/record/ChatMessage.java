package com.employee.managment.conversation.record;

import java.time.LocalDateTime;

public record ChatMessage(
        String textMessage,
        String profileId,
        LocalDateTime messageTime

) {
}
