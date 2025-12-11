package com.prajwal.agrolive.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    public boolean loginCheck(String email, String password) {

        User user = userRepo.findByEmail(email);

        if (user == null) {
            System.out.println("User not found with email: " + email);
            return false; 
        }
        
      

        System.out.println("User found: " + user);
        System.out.println("Password in DB: " + user.getPassword());
        System.out.println("Password entered: " + password);

        return user.getPassword().equals(password);
    }
    
    

}
