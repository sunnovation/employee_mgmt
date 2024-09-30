package com.employee.managment.profiles.record;

public record EmployeeProfile(
        String id,
        String empName,
        String location,
        String client,
        String designation,
        String phoneno,
        String gender,
        String billingStatus,
        String grade

) {
}
