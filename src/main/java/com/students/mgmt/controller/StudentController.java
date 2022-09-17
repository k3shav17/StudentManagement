package com.students.mgmt.controller;

import com.students.mgmt.model.Student;
import com.students.mgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/home")
    public String studentManagement() {
        return "Welcome Student management system";
    }

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/mail/{mailId}")
    public ResponseEntity<Optional<Student>> getByMail(@PathVariable("mailId") String mail) {
        return new ResponseEntity<>(studentService.findByMail(mail), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/fname/{name}")
    public ResponseEntity<Optional<Student>> getByFirstName(@PathVariable("name") String firstName) {
        return new ResponseEntity<>(studentService.findByFirstName(firstName), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/standard/{class}")
    public ResponseEntity<List<Student>> getStudentsOfSameClass(@PathVariable("class") String standard) {
        return new ResponseEntity<>(studentService.getAllStudentsFromSameClass(standard), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{mail}")
    public ResponseEntity<String> deleteByMail(@PathVariable("mail") String mail) {
        studentService.deleteByMail(mail);
        return new ResponseEntity<>(String.format("Record with mail id as %s has been removed.", mail), new HttpHeaders(), HttpStatus.OK);
    }

}
