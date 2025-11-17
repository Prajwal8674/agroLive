package com.prajwal.agrolive.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prajwal.agrolive.userEntity.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
