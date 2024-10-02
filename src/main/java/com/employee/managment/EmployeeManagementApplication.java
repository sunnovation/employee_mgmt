package com.employee.managment;

import com.employee.managment.profiles.record.UserProfile;
import com.employee.managment.profiles.repository.UserProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EmployeeManagementApplication  {



	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}


}
