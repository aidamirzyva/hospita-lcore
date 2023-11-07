package com.bookstore.service;


import com.bookstore.dto.request.ReqTransaction;
import com.bookstore.dto.response.RespTransaction;
import com.bookstore.dto.response.Response;

import java.util.List;

public interface TransactionService {
    Response<List<RespTransaction>> getTransactionList(Long paymentId);

    Response createOperation(ReqTransaction reqTransaction);
}
