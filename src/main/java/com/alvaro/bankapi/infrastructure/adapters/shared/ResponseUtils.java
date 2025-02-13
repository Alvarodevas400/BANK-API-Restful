package com.alvaro.bankapi.infrastructure.adapters.shared;

import org.springframework.http.HttpStatus;

public class ResponseUtils {

    public static HttpStatus mapCodeToHttpStatus(int code) {
        return switch (code) {
            case 0 -> HttpStatus.OK;
            case 1, 2, 20, 29 -> HttpStatus.NOT_FOUND;
            default -> {
                HttpStatus badRequest;
                if (code > 2) {
                    badRequest = HttpStatus.BAD_REQUEST;
                } else {
                    badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                yield badRequest;
            }
        };
    }
}
