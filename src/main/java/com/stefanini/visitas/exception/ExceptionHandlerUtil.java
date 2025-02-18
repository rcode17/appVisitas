package com.stefanini.visitas.exception;

import com.microsoft.azure.functions.*;
import com.stefanini.visitas.dto.ErrorResponse;

public class ExceptionHandlerUtil {
    
    public static HttpResponseMessage handleException(HttpRequestMessage<?> request, Exception e, ExecutionContext context) {
        context.getLogger().severe("Error en la ejecución: " + e.getMessage());

        ErrorResponse errorResponse;
        HttpStatus status;

        if (e instanceof IllegalArgumentException) {
            status = HttpStatus.NOT_FOUND;
            errorResponse = new ErrorResponse(404, e.getMessage());
        } else if (e instanceof IllegalStateException) {
            status = HttpStatus.BAD_REQUEST;
            errorResponse = new ErrorResponse(400, e.getMessage());
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errorResponse = new ErrorResponse(500, "Error interno del servidor. Contacte al soporte.");
        }

        return request.createResponseBuilder(status)
                .header("Content-Type", "application/json")  // 🔹 Establecer JSON
                .body(errorResponse)
                .build();
    }
}
