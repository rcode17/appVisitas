package com.stefanini.visitas.exception;

import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.stefanini.visitas.dto.ErrorResponse;

public class ExceptionHandlerUtil {
    
    public static HttpResponseMessage handleException(HttpRequestMessage<?> request, Exception e) {
        if (e instanceof IllegalArgumentException) {
            return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, e.getMessage()))
                    .build();
        } else if (e instanceof IllegalStateException) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, e.getMessage()))
                    .build();
        } else {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(500, "Error interno del servidor"))
                    .build();
        }
    }
}