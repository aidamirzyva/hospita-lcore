package com.bookstore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqStudent {

    private Long studentId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date dob;

    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
