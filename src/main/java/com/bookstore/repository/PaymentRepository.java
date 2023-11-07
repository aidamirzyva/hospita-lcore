package com.bookstore.repository;

import com.bookstore.entity.Payment;
import com.bookstore.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByStudentAndActive(Student student, Integer active);
    Payment findPaymentByIdAndActive(Long id, Integer active);
}
