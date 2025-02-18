package com.stefanini.visitas.functions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.stefanini.visitas.dto.ErrorResponse;
import com.stefanini.visitas.exception.ExceptionHandlerUtil;
import com.stefanini.visitas.modelo.Masa;
import com.stefanini.visitas.service.MasaService;

@Component
public class MasaFunctions {

    private final MasaService masaService;

    @Autowired
    public MasaFunctions(MasaService masaService) {
        this.masaService = masaService;
    }

    @FunctionName("obtenerMasaPorUsuario")
    public HttpResponseMessage obtenerMasaPorUsuario(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "masa/usuario/{user}", authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        @BindingName("user") String user,
        final ExecutionContext context) {

        context.getLogger().info("Procesando solicitud para obtener masa del usuario: " + user);

        try {
            List<Masa> masas = masaService.obtenerPorUsuario(user);
            if (masas.isEmpty()) {
                return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                        .header("Content-Type", "application/json")
                        .body(new ErrorResponse(404, "No se encontraron masas para el usuario: " + user))
                        .build();
            }
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(masas)
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }
}
