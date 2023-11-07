package com.bookstore.dto.request;

import lombok.Data;

@Data
public class ReqTransaction {

    private Long paymentId;
    private Integer amount;

}
