package com.bookstore.service.impl;

import com.bookstore.dto.request.ReqPayment;
import com.bookstore.dto.response.RespPayment;
import com.bookstore.dto.response.RespStatus;
import com.bookstore.dto.response.Response;
import com.bookstore.entity.Book;
import com.bookstore.entity.Payment;
import com.bookstore.entity.Student;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.repository.StudentRepository;
import com.bookstore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BookRepository bookRepository;

    private final StudentRepository studentRepository;

    private final PaymentRepository paymentRepository;
    @Override
    public Response<List<RespPayment>> getPaymentByStudentId(Long studentId) {
//        ReqBook reqBook = new ReqBook();
        Response<List<RespPayment>> response = new Response<>();
//        Long bookId = reqBook.getBookId();
        try {
            if (studentId == null ) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = studentRepository.findStudentByIdAndActive(studentId, EnumAviableStatus.ACTIVE.value);
            if (student == null) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "student not found");
            }


            List<Payment> paymentList = paymentRepository.findAllByStudentAndActive(student, EnumAviableStatus.ACTIVE.value);


            //List<User> userList= userRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (paymentList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            List<RespPayment> respPaymentList = paymentList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respPaymentList);
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
    public Response addPayment(ReqPayment reqPayment) {
        Response response = new Response<>();
        try {
            Long studentId =  reqPayment.getCustomerId();
            Long bookId = reqPayment.getBookId();
            Integer amount = reqPayment.getAmount();
            if(studentId == null || bookId == null || amount == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAviableStatus.ACTIVE.value);
            if (book == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            Student student = studentRepository.findStudentByIdAndActive(studentId, EnumAviableStatus.ACTIVE.value);
            if (student == null) {
                throw new LibraryException(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");
            }
            Payment payment = Payment.builder()
                    .student(student)
                    .book(book)
                    .amount(amount)
                    .build();
            paymentRepository.save(payment);
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

    private RespPayment mapping(Payment payment) {
        RespPayment respPayment = RespPayment.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .build();
        return respPayment;
    }

}
