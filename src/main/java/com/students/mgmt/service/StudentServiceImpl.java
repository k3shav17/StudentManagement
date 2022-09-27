package com.students.mgmt.service;

import com.students.mgmt.exceptions.StudentExistsException;
import com.students.mgmt.exceptions.StudentNotFoundException;
import com.students.mgmt.model.Student;
import com.students.mgmt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    protected StudentRepository studentRepository;

    @Override
    public Student save(Student student) {

        if (studentRepository.findByMail(student.getMail()).isPresent()) {
            throw new StudentExistsException(String.format("Student with mail id %s exists in the system", student.getMail()));
        }
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

    @Override
    public Optional<Student> findByMail(String mail) {
        if (studentRepository.findByMail(mail).isPresent()) return studentRepository.findByMail(mail);
        else throw new StudentNotFoundException(String.format("No student found with mail %s", mail));
    }

    @Override
    public Optional<Student> findByFirstName(String firstName) {
        if (studentRepository.findByFirstName(firstName).isPresent())
            return studentRepository.findByFirstName(firstName);
        else throw new StudentNotFoundException(String.format("Student not found with first name %s", firstName));
    }

    @Override
    public void deleteByMail(String mail) {
        if (findByMail(mail).isPresent()) studentRepository.deleteByMail(mail);
        else throw new StudentNotFoundException(String.format("No student has found with mail %s", mail));
    }

    @Override
    public void deleteByFirstName(String firstName) {
        if (findByFirstName(firstName).isPresent()) studentRepository.deleteStudentByFirstName(firstName);
        else throw new StudentNotFoundException(String.format("No student has found with firstname %s", firstName));
    }

    @Override
    public void updateDetails(Student student) {
        if (findByMail(student.getMail()).isPresent()) {
            Optional<Student> tempStudentDetails = findByMail(student.getMail());
            Long stuId;
            if (tempStudentDetails.isPresent()) {
                stuId = tempStudentDetails.get().getId();
                updatingDetailsHelper(stuId, student);
            }
        } else
            throw new StudentNotFoundException(String.format("No record with student named %s", student.getFirstName()));
    }

    private Optional<Student> updatingDetailsHelper(Long id, Student student) {
       return studentRepository.findById(id).map(stu -> {
            stu.setFirstName(student.getFirstName());
            stu.setLastName(student.getLastName());
            stu.setAge(student.getAge());
            stu.setMail(student.getMail());
            stu.setGender(student.getGender());
            stu.setDateOfBirth(student.getDateOfBirth());
            stu.setStandard(student.getStandard());
            stu.setContactNumber(student.getContactNumber());
            stu.setAddress(student.getAddress());

            return studentRepository.save(stu);
        });
    }
}
