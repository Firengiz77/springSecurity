package com.bookstore.spring_security_auth.service;



import com.bookstore.spring_security_auth.model.Course;

import java.util.Optional;

public interface CourseService {

    Course createCourse(Course course);

    Optional<Course> findCourseById(Long courseId);

    Iterable<Course> findAllCourses();

    Course updateCourse(Course course);

    void deleteCourseById(Long courseId);

}
