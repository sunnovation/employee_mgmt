package com.employee.managment.ai.record;

import com.employee.managment.conversation.record.ChatMessage;

public record AIResponseOfEmployee(String id,
                                   String profileId,
                                   ChatMessage message,
                                   String chatResponse) {
}
