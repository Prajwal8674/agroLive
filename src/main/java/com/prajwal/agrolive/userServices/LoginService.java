package com.prajwal.agrolive.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    public User loginCheckAndGetUser(String email, String password) {

        // Method 1: Using Optional.orElse()
        Optional<User> optionalUser = userRepo.findByEmail(email);
        
        if (!optionalUser.isPresent()) {
            return null; // User not found
        }
        
        User user = optionalUser.get();

        if (user.getPassword().equals(password)) {
            return user; // ✅ success
        }

        return null; // Password doesn't match
    }
}