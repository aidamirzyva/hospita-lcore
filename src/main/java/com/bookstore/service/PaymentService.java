package com.bookstore.service;

import com.bookstore.dto.request.ReqPayment;
import com.bookstore.dto.response.RespPayment;
import com.bookstore.dto.response.Response;

import java.util.List;

public interface PaymentService {
    Response<List<RespPayment>> getPaymentByStudentId(Long studentId);

    Response addPayment(ReqPayment reqPayment);
}
