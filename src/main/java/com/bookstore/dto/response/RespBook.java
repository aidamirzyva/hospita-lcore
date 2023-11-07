package com.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespBook {

    private Long bookId;
    private String name;
    private String author;
    private String lang;
    private Integer year;
    private Integer amount;



}
