package com.bookstore.spring_security_auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecaptchaDto {

    private boolean success;
    private List<String> errors;

}
