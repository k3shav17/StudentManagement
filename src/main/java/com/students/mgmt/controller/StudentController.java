package com.students.mgmt.controller;

import com.students.mgmt.exceptions.StudentExistsException;
import com.students.mgmt.exceptions.StudentNotFoundException;
import com.students.mgmt.model.Student;
import com.students.mgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Student student) {
        //TODO: Add DTO
        try {
            Student savedStudent = studentService.save(student);
            return new ResponseEntity<>(savedStudent, new HttpHeaders(), HttpStatus.CREATED);
        } catch (StudentExistsException see) {
            return new ResponseEntity<>(see.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/mail/{mailId}")
    public ResponseEntity<Object> getByMail(@PathVariable("mailId") String mail) {
        try {
            return new ResponseEntity<>(studentService.findByMail(mail), new HttpHeaders(), HttpStatus.OK);
        } catch (StudentNotFoundException snfe) {
            return new ResponseEntity<>(snfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fname/{name}")
    public ResponseEntity<Object> getByFirstName(@PathVariable("name") String firstName) {
        try {
            return new ResponseEntity<>(studentService.findByFirstName(firstName), new HttpHeaders(), HttpStatus.OK);
        } catch (StudentNotFoundException snfe) {
            return new ResponseEntity<>(snfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/standard/{class}")
    public ResponseEntity<List<Student>> getStudentsOfSameClass(@PathVariable("class") String standard) {
        return new ResponseEntity<>(studentService.getAllStudentsFromSameClass(standard), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/mail/{mail}")
    public ResponseEntity<String> deleteByMail(@PathVariable("mail") String mail) {
        try {
            studentService.deleteByMail(mail);
            return new ResponseEntity<>(String.format("Record with mail id %s has been removed.", mail), new HttpHeaders(), HttpStatus.OK);
        } catch (StudentNotFoundException snfe) {
            return new ResponseEntity<>(snfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/fname/{firstname}")
    public ResponseEntity<String> deleteByFirstName(@PathVariable("firstname") String firstName) {
        try {
            studentService.deleteByFirstName(firstName);
            return new ResponseEntity<>(String.format("Record with first name %s has been removed.", firstName), new HttpHeaders(), HttpStatus.OK);
        } catch (StudentNotFoundException snfe) {
            return new ResponseEntity<>(snfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Student student) {
        try {
            studentService.updateDetails(student);
            return new ResponseEntity<>(String.format("Details of student with name %s and mail %s has been updated", student.getFirstName(), student.getMail()), new HttpHeaders(), HttpStatus.OK);
        } catch (StudentNotFoundException snfe) {
            return new ResponseEntity<>(snfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
