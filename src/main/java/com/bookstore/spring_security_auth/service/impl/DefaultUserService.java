package com.bookstore.spring_security_auth.service.impl;


import com.bookstore.spring_security_auth.dto.UserDto;
import com.bookstore.spring_security_auth.model.ApplicationUser;
import com.bookstore.spring_security_auth.repository.UserRepository;
import com.bookstore.spring_security_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser createUser(UserDto userDto) {
       ApplicationUser applicationUser = new ApplicationUser();
       applicationUser.setFirstName(userDto.getFirstName());
       applicationUser.setLastName(userDto.getLastName());
       applicationUser.setEmail(userDto.getEmail());
       applicationUser.setUsername(userDto.getUsername());
       applicationUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

       return userRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser save(ApplicationUser applicationUser) {
        return userRepository.save(applicationUser);
    }


    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
