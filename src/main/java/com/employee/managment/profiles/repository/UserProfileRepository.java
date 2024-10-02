package com.employee.managment.profiles.repository;

import com.employee.managment.profiles.record.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile,String> {
}
