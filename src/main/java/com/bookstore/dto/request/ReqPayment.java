package com.bookstore.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqPayment {

    private Long id;
    private Long studentId;
    private Long bookId;
    private Integer amount;

}
