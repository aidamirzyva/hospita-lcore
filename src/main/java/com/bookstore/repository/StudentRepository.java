package com.bookstore.repository;

import com.bookstore.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByActive (Integer active);
    Student findStudentByIdAndActive(Long studentId, Integer active);
}
