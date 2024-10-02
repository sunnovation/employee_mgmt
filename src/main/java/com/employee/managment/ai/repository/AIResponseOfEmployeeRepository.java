package com.employee.managment.ai.repository;

import com.employee.managment.ai.record.AIResponseOfEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AIResponseOfEmployeeRepository extends MongoRepository<AIResponseOfEmployee,String> {
}
