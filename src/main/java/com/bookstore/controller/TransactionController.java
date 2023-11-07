package com.bookstore.controller;


import com.bookstore.dto.request.ReqTransaction;
import com.bookstore.dto.response.RespTransaction;
import com.bookstore.dto.response.Response;
import com.bookstore.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/GetTransaction/{paymentId}")
    public Response<List<RespTransaction>> getTransactionList(@PathVariable Long paymentId) {

        return transactionService.getTransactionList(paymentId);
    }

    @PostMapping("/CreateOperation")
    public Response createOperation(@RequestBody ReqTransaction reqTransaction) {
        return transactionService.createOperation(reqTransaction);
    }

}
