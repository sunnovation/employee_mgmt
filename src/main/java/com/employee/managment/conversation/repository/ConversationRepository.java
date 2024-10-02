package com.employee.managment.conversation.repository;

import com.employee.managment.conversation.record.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation,String> {
}
