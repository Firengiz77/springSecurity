package com.bookstore.spring_security_auth.repository;

import com.bookstore.spring_security_auth.model.EmailVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {

    EmailVerification findByUsername(String userName);
    boolean existsByUsername(String userName);
}

