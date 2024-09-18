package com.bookstore.spring_security_auth.listener;


import com.bookstore.spring_security_auth.event.UserRegistrationEvent;
import com.bookstore.spring_security_auth.model.ApplicationUser;
import com.bookstore.spring_security_auth.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationListener;

import java.util.Base64;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final EmailVerificationService verificationService;

    @Autowired
    public EmailVerificationListener(JavaMailSender mailSender, EmailVerificationService verificationService) {
        this.mailSender = mailSender;
        this.verificationService = verificationService;
    }

    public void onApplicationEvent(UserRegistrationEvent event) {
    	ApplicationUser user = event.getUser();
        String username = user.getUsername();
        String verificationId = verificationService.generateVerification(username);
        String email = event.getUser().getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Course Tracker Account Verification");
        message.setText(getText(user, verificationId));
        message.setTo(email);
        mailSender.send(message);
    }
    
    private String getText(ApplicationUser user, String verificationId) {
    	String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("Dear ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(",").append(System.lineSeparator()).append(System.lineSeparator());
    	buffer.append("Your account has been successfully created in the Course Tracker application. ");
    	
    	buffer.append("Activate your account by clicking the following link: http://localhost:8080/verify/email?id=").append(encodedVerificationId);
    	buffer.append(System.lineSeparator()).append(System.lineSeparator());
    	buffer.append("Regards,").append(System.lineSeparator()).append("Course Tracker Team");
    	return buffer.toString();
    }
}
