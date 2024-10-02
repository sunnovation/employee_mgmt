package com.employee.managment.profiles.service;

import com.employee.managment.profiles.common.EmployeeProfileRequest;
import com.employee.managment.profiles.constant.Level;
import com.employee.managment.profiles.constant.ProfileProperties;
import com.employee.managment.profiles.record.BillingStatus;
import com.employee.managment.profiles.record.EmployeeProfile;
import com.employee.managment.profiles.repository.EmployeeProfileRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;


@Service
public class EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeProfileService(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    public HashMap<String, Object> createEmployeeProfile(EmployeeProfileRequest employeeProfileRequest) {

        HashMap<String,Object> response=new HashMap<>();
       try {
           response.put("status", true);
           EmployeeProfile profile = new EmployeeProfile(

                   UUID.randomUUID().toString(),
                   employeeProfileRequest.getEmployeeProfile().empName(),
                   employeeProfileRequest.getEmployeeProfile().location(),
                   employeeProfileRequest.getEmployeeProfile().client(),
                   employeeProfileRequest.getEmployeeProfile().designation(),
                   employeeProfileRequest.getEmployeeProfile().phoneno(),
                   employeeProfileRequest.getEmployeeProfile().gender(),
                   employeeProfileRequest.getEmployeeProfile().billingStatus(),
                   employeeProfileRequest.getEmployeeProfile().grade());
           response.put("data", employeeProfileRepository.save(profile));
           response.put("code", "201");
       }catch(Exception ex){
           response.put("status", false);
           response.put("error", ex);
           response.put("code", "500");
       }
       return response;
    }

    public HashMap<String, Object> filterEmployeeProfile(String level,String filter, String data) {
        HashMap<String,Object> response=new HashMap<>();
        try{
            response.put("status", true);
            if(level.equalsIgnoreCase(Level.FIRST_LEVEL.name())) {
                if (filter.equalsIgnoreCase(ProfileProperties.LOCATION.toString())) {
                    response.put("data", employeeProfileRepository.findByLocation(data));
                } else if (filter.equalsIgnoreCase(ProfileProperties.GENDER.toString())) {
                    response.put("data", employeeProfileRepository.findByGender(data));
                } else if (filter.equalsIgnoreCase(ProfileProperties.CLIENT.toString())) {
                    response.put("data", employeeProfileRepository.findByClient(data));
                } else if (filter.equalsIgnoreCase(ProfileProperties.DESIGNATION.toString())) {
                    response.put("data", employeeProfileRepository.findByDesignation(data));
                } else if (filter.equalsIgnoreCase(ProfileProperties.GRADE.name())) {
                    response.put("data", employeeProfileRepository.findByGrade(data));
                }
            } else if(level.equalsIgnoreCase(Level.SECOND_LEVEL.name())){

            }
            response.put("code", "200");
        }catch(Exception ex){
            response.put("status", false);
            response.put("error", ex);
            response.put("code", "500");
        }

        return response;

    }
}
