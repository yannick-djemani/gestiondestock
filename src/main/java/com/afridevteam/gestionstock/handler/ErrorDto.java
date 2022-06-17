package com.afridevteam.gestionstock.handler;

import com.afridevteam.gestionstock.exception.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    List<String> errors = new ArrayList<>();
}
