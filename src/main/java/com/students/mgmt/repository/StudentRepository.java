package com.students.mgmt.repository;

import com.students.mgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByMail(String mail);
//
    Optional<Student> findByFirstName(String firstName);

    List<Student> findAllByStandard(String standard);

    @Transactional
    void deleteByMail(String mail);
//
//
//    Optional<Student> DeleteByFirstName(String lastName);

//    List<Student> getStudentsWithSameNameInAClass(String firstName, String standard);

}
