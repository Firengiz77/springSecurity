package com.bookstore.spring_security_auth.event;


import com.bookstore.spring_security_auth.model.ApplicationUser;
import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {

	private static final long serialVersionUID = -2685172945219633123L;
	
	private ApplicationUser applicationUser;

    public UserRegistrationEvent(ApplicationUser applicationUser) {
        super(applicationUser);
        this.applicationUser = applicationUser;
    }

    public ApplicationUser getUser() {
        return applicationUser;
    }
}
