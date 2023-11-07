package com.bookstore.repository;


import com.bookstore.entity.Payment;
import com.bookstore.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByPaymentAndActive(Payment payment , Integer active);
}
