package com.bookstore.enums;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RespCheckout {
    public Long id;
    private Double totalAmount;
    private LocalDate invoiceDate;
}
