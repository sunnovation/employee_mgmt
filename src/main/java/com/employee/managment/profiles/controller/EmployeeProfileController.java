package com.employee.managment.profiles.controller;

import com.employee.managment.profiles.common.EmployeeProfileRequest;
import com.employee.managment.profiles.service.EmployeeProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class EmployeeProfileController {


    private final EmployeeProfileService employeeProfileService;



    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping("/employee")
    public ResponseEntity<HashMap<String,Object>> createEmployeeProfile(@RequestBody EmployeeProfileRequest employeeProfileRequest){
            return new ResponseEntity<>(employeeProfileService.createEmployeeProfile(employeeProfileRequest), HttpStatus.CREATED);
    }

    @GetMapping("/filter")
    public ResponseEntity<HashMap<String,Object>> filterEmployeeProfile(@RequestParam String filter,@RequestParam String data){
        return new ResponseEntity<>(employeeProfileService.filterEmployeeProfile(filter,data),HttpStatus.OK);
    }

}
