package com.employee.managment.profiles.config;

import com.employee.managment.profiles.record.EmployeeProfile;
import com.employee.managment.profiles.record.UserProfile;
import com.employee.managment.profiles.repository.UserProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Configuration
public class ProfileConfiguration {

    private final UserProfileRepository userProfileRepository;

    public ProfileConfiguration(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @PostConstruct
   public void  addDefaultUserProfile(){
       userProfileRepository.deleteAll();
       List<UserProfile> profiles =
               List.of(
                 new UserProfile(UUID.randomUUID().toString(),
                         "TIAA HR Department",
                         "TIAA HR",
                         "PUNE",
                         "TIAA",
                         "7845125689",
                         "MALE",
                         "N/A",
                         "N/A"
                 ),
                       new UserProfile(UUID.randomUUID().toString(),
                               "TIAA PMO Department",
                               "TIAA PMO",
                               "PUNE",
                               "TIAA",
                               "8845125689",
                               "FEMALE",
                               "N/A",
                               "N/A"
                       )

               );
       userProfileRepository.saveAll(profiles);
   }

}
