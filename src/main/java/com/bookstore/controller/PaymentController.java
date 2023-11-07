package com.bookstore.controller;

import com.bookstore.dto.request.ReqPayment;
import com.bookstore.dto.response.RespPayment;
import com.bookstore.dto.response.Response;
import com.bookstore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @GetMapping("/GetPaymentListByUserId/{studentId}")
    public Response<List<RespPayment>> getPaymentListByStudentId(@PathVariable Long studentId) {
        return paymentService.getPaymentByStudentId(studentId);
    }

    @PostMapping("/AddPayment")
    public Response addPayment(@RequestBody ReqPayment reqPayment) {
        return paymentService.addPayment(reqPayment);
    }

}
