package com.students.mgmt.repository;

import com.students.mgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByMail(String mail);

    Optional<Student> findByFirstName(String firstName);

    List<Student> findAllByStandard(String standard);

    @Transactional
    void deleteByMail(String mail);

    @Modifying
    @Transactional
    @Query(value = "DELETE student, address FROM student INNER JOIN address ON address.id = student.id WHERE first_name = :firstName", nativeQuery = true)
    void deleteStudentByFirstName(@Param("firstName") String firstName);

//    List<Student> getStudentsWithSameNameInAClass(String firstName, String standard);

}
