package com.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespAuthor {

    private Long authorId;
    private String name;
    private String surname;
    private String email;
    private String phone;

}
