package com.avishek.accounts.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class CustomerDto {

    @Getter
    private String name;

    private String mobileNumber;
    private String email;
}
