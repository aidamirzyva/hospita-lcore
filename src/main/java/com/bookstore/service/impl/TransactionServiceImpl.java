package com.bookstore.service.impl;

import com.bookstore.dto.request.ReqTransaction;
import com.bookstore.dto.response.*;
import com.bookstore.entity.Payment;
import com.bookstore.entity.Transaction;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.repository.TransactionRepository;
import com.bookstore.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Response<List<RespTransaction>> getTransactionList(Long paymentId) {
        Response<List<RespTransaction>> response = new Response<>();
        try {
            if (paymentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Payment payment = paymentRepository.findPaymentByIdAndActive(paymentId, EnumAviableStatus.ACTIVE.value);
            if (payment == null) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            List <Transaction> transactionList = transactionRepository.findAllByPaymentAndActive(payment, EnumAviableStatus.ACTIVE.value);
            List<RespTransaction> respTransactionList = transactionList.stream().map(this::mapping).collect(Collectors.toList());
            if (respTransactionList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction not found");
            }
            response.setT(respTransactionList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response createOperation(ReqTransaction reqTransaction) {
        Response response = new Response<>();
        try {
            Integer amount = reqTransaction.getAmount();
            Long paymentId = reqTransaction.getPaymentId();
            if (amount == null || paymentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            if (amount <= 0) {
                throw new LibraryException(ExceptionConstants.INVALID_AMOUNT, "Invalid amount");
            }
            Payment payment = paymentRepository.findPaymentByIdAndActive(paymentId, EnumAviableStatus.ACTIVE.value);
            if (payment == null) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            Transaction transaction = new Transaction();
            transaction.builder()
                    .payment(payment)
                    .amount(amount)
                    .build();
            transactionRepository.save(transaction);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    private RespTransaction mapping( Transaction transaction) {
        RespStudent respStudent = RespStudent.builder()
                .studentId(transaction.getPayment().getStudent().getId())
                .name(transaction.getPayment().getStudent().getName())
                .surname(transaction.getPayment().getStudent().getSurname())
                .dob(transaction.getPayment().getStudent().getDob())
                .phone(transaction.getPayment().getStudent().getPhone())
                .build();
        RespPayment respPayment = RespPayment.builder()
                .amount(transaction.getPayment().getAmount())
                .build();
        RespTransaction respTransaction = RespTransaction.builder()
                .amount(transaction.getAmount())
                .payment(respPayment)
                .build();
        return respTransaction;
    }

}
