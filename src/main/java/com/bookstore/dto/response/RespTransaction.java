package com.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

@Data
@Builder
public class RespTransaction {

    private Long id;
    private RespPayment payment;
    private Integer amount;

}
