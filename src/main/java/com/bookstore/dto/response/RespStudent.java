package com.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespStudent {

    private Long studentId;
    private String name;
    private String surname;
    private String email;
    private Date dob;
    private String phone;

}
