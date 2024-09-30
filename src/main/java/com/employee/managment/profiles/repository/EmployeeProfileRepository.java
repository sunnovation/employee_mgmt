package com.employee.managment.profiles.repository;

import com.employee.managment.profiles.record.EmployeeProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeProfileRepository extends MongoRepository<EmployeeProfile,String> {
    List<EmployeeProfile> findByLocation(String data);

    List<EmployeeProfile> findByGender(String data);

    List<EmployeeProfile> findByClient(String data);

    List<EmployeeProfile> findByDesignation(String data);

    List<EmployeeProfile> findByGrade(String data);
}
