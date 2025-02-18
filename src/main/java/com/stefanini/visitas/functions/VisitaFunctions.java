package com.stefanini.visitas.functions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.stefanini.visitas.dto.ErrorResponse;
import com.stefanini.visitas.dto.VisitaDTO;
import com.stefanini.visitas.exception.ExceptionHandlerUtil;
import com.stefanini.visitas.modelo.Visita;
import com.stefanini.visitas.service.VisitaService;

@Component
public class VisitaFunctions {

    private final VisitaService visitaService;

    @Autowired
    public VisitaFunctions(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @FunctionName("obtenerVisitas")
    public HttpResponseMessage obtenerTodasLasVisitas(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Obteniendo todas las visitas");

        try {
            List<Visita> visitas = visitaService.obtenerTodasLasVisitas();
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(visitas)
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("obtenerVisitasV2")
    public HttpResponseMessage obtenerVisitas(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "v2")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        String idVisitaStr = request.getQueryParameters().get("idVisita");
        String user = request.getQueryParameters().get("user");

        context.getLogger().info("Obteniendo visitas con idVisita=" + idVisitaStr + " y user=" + user);

        try {
            if (idVisitaStr != null) {
                Integer idVisita = Integer.parseInt(idVisitaStr);
                return request.createResponseBuilder(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(Collections.singletonList(visitaService.obtenerPorId(idVisita)))
                        .build();
            }

            if (user != null && !user.isEmpty()) {
                return request.createResponseBuilder(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(visitaService.findByUser(user))
                        .build();
            }

            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(visitaService.obtenerTodasLasVisitas())
                    .build();

        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .body(new ErrorResponse(400, "El idVisita debe ser un número válido."))
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("obtenerVisitaPorId")
    public HttpResponseMessage obtenerVisitaPorId(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "visitas/{id}")
            HttpRequestMessage<Optional<String>> request,
            @BindingName("id") String idVisitaStr,
            final ExecutionContext context) {

        context.getLogger().info("Buscando visita con ID: " + idVisitaStr);
        ErrorResponse mensaje = new ErrorResponse(200, "Hola Mundo");
        
        try {
            /*Integer idVisita = Integer.parseInt(idVisitaStr);
            Visita visita = visitaService.obtenerPorId(idVisita);*/
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(mensaje)
                    .build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("eliminarVisita")
    public HttpResponseMessage eliminarVisita(
            @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, authLevel = AuthorizationLevel.ANONYMOUS, route = "visitas/{id}")
            HttpRequestMessage<Optional<String>> request,
            @BindingName("id") String idVisitaStr,
            final ExecutionContext context) {

        context.getLogger().info("Eliminando visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            visitaService.eliminarVisita(idVisita);
            return request.createResponseBuilder(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("guardarVisita")
    public HttpResponseMessage guardarVisita(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<VisitaDTO>> request,
            final ExecutionContext context) {

        context.getLogger().info("Guardando una nueva visita");

        try {
            VisitaDTO visita = request.getBody().orElseThrow(() -> new IllegalArgumentException("El cuerpo de la solicitud está vacío."));
            Visita nuevaVisita = visitaService.guardarVisita(visita);
            return request.createResponseBuilder(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(nuevaVisita)
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("editarVisita")
    public HttpResponseMessage editarVisita(
            @HttpTrigger(name = "req", methods = {HttpMethod.PUT}, authLevel = AuthorizationLevel.ANONYMOUS, route = "visitas/{id}")
            HttpRequestMessage<Optional<VisitaDTO>> request,
            @BindingName("id") String idVisitaStr,
            final ExecutionContext context) {

        context.getLogger().info("Editando visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            VisitaDTO visita = request.getBody().orElseThrow(() -> new IllegalArgumentException("El cuerpo de la solicitud está vacío."));
            Visita visitaEditada = visitaService.editarVisita(idVisita, visita);
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(visitaEditada)
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    @FunctionName("obtenerVisitasPorUsuario")
    public HttpResponseMessage obtenerVisitasPorUsuario(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "visitas/usuario/{user}", authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @BindingName("user") String user,
            final ExecutionContext context) {

        context.getLogger().info("Procesando solicitud para obtener visitas del usuario: " + user);

        try {
            List<Visita> visitas = visitaService.findByUser(user);
            if (visitas.isEmpty()) {
                return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                        .header("Content-Type", "application/json")
                        .body(new ErrorResponse(404, "No se encontraron visitas para el usuario: " + user))
                        .build();
            }
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(visitas)
                    .build();

        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }
}