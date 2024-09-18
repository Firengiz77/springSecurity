package com.bookstore.spring_security_auth.controller;


import com.bookstore.spring_security_auth.dto.RecaptchaDto;
import com.bookstore.spring_security_auth.model.EmailVerification;
import com.bookstore.spring_security_auth.service.UserService;
import com.bookstore.spring_security_auth.service.impl.GoogleRecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bookstore.spring_security_auth.dto.UserDto;
import jakarta.validation.Valid;
import com.bookstore.spring_security_auth.event.UserRegistrationEvent;
import com.bookstore.spring_security_auth.model.ApplicationUser;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private GoogleRecaptchaService captchaService;

    @Value("${app.email.verification:N}")
    private String emailVerification;


    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest httpServletRequest, BindingResult result) {
        if(result.hasErrors()) {
            return "add-user";
        }
        String response = httpServletRequest.getParameter("g-recaptcha-response"); if(response == null) {
        return "add-user";
    }
            String ip = httpServletRequest.getRemoteAddr();
    RecaptchaDto recaptchaDto = captchaService.verify(ip, response); if(!recaptchaDto.isSuccess()) {
        return "redirect:adduser?incorrectCAPTCHA";
    }
    ApplicationUser applicationUser = userService.createUser(userDto);
if("Y".equalsIgnoreCase(emailVerification)) {
        eventPublisher.publishEvent(new UserRegistrationEvent(applicationUser)); return "redirect:adduser?validate";
    }
    return "redirect:adduser?success";
}




//    @PostMapping("/adduser")
//    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
//        if(result.hasErrors()) {
//            return "add-user";
//        }
//        ApplicationUser applicationUser = userService.createUser(userDto);
//        eventPublisher.publishEvent(new UserRegistrationEvent(applicationUser));
//        return "redirect:adduser?validate";
//
////        userService.createUser(userDto);
////        return "redirect:adduser?success";
//    }
}
