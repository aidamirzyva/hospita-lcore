package com.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespPayment {

    private Long id;
    private Integer amount;
    private RespStudent respCustomer;

}
