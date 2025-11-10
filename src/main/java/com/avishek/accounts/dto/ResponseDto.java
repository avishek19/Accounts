package com.avishek.accounts.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data @AllArgsConstructor @NoArgsConstructor

public class ResponseDto {

    private String statusCode;
    private String statusMessage;



//    public ResponseDto(String statusCode, String statusMessage) {
//    }

}
