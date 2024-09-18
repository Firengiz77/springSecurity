package com.bookstore.spring_security_auth.repository;


import com.bookstore.spring_security_auth.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
