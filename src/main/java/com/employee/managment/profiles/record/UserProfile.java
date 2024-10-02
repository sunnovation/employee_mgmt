package com.employee.managment.profiles.record;

public record UserProfile(
        String id,
        String name,
        String designation,
        String location,
        String client,
        String phoneno,
        String gender,
        String billingStatus,
        String grade

) {
}
