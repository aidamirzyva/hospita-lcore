package com.bookstore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqAuthor {

    private Long authorId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
