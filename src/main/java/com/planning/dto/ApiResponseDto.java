package com.planning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ApiResponseDto {
    private int statusCode;
    private String message;
}