package com.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespUser {

    private String username;
    private String fullName;
    @JsonProperty(value = "token") //obyekt kimi qaytarir
    private RespToken respToken;

}
