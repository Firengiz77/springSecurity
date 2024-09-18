package com.bookstore.spring_security_auth.service;


import com.bookstore.spring_security_auth.dto.UserDto;
import com.bookstore.spring_security_auth.model.ApplicationUser;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
    ApplicationUser save(ApplicationUser applicationUser);
    ApplicationUser findByUsername(String username);
}
