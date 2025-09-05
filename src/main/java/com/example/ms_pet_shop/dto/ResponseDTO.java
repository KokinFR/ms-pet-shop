package com.example.ms_pet_shop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String message;
    private Integer statusHttp;
}
