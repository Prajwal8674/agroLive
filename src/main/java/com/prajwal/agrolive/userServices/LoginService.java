package com.prajwal.agrolive.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    public User loginCheckAndGetUser(String email, String password) {

        User user = userRepo.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user; // ✅ success
        }

        return null;
    }
}
