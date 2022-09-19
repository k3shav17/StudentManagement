package com.students.mgmt.service;

import com.students.mgmt.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student save(Student student);

    void deleteById(Long id);

    List<Student> getStudents();

    List<Student> getAllStudentsFromSameClass(String standard);

    Optional<Student> findByMail(String mail);

    Optional<Student> findByFirstName(String firstName);

    void deleteByMail(String mail);

    void deleteByFirstName(String firstName);


}
