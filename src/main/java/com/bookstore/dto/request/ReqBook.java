package com.bookstore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqBook {

    private Long bookId;
    private String name;
    private String author;
    private String lang;
    private Integer year;
    private Integer amount;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
