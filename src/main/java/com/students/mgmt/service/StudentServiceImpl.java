package com.students.mgmt.service;

import com.students.mgmt.model.Student;
import com.students.mgmt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentsFromSameClass(String standard) {
        return studentRepository.findAllByStandard(standard);
    }

    //    @Override
//    public void deleteById(Long id) {
//        studentRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<Student> findById(Long id) {
//        return studentRepository.findById(id);
//    }
//
    @Override
    public Optional<Student> findByMail(String mail) {
        return studentRepository.findByMail(mail);
    }

    //
    @Override
    public Optional<Student> findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public void deleteByMail(String mail) {
        if (findByMail(mail).isPresent()) studentRepository.deleteByMail(mail);
        else throw new RuntimeException(String.format("No student has found with mail %s", mail));
    }

//    @Override
//    public Optional<Student> DeleteByFirstName(String firstName) {
//        return studentRepository.DeleteByFirstName(firstName);
//}

}
