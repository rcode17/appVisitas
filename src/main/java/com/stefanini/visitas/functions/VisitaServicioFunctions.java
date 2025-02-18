package com.stefanini.visitas.functions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.stefanini.visitas.dto.VisitaServicioDTO;
import com.stefanini.visitas.exception.ExceptionHandlerUtil;
import com.stefanini.visitas.modelo.VisitaServicio;
import com.stefanini.visitas.service.VisitaServicioService;

@Component
public class VisitaServicioFunctions {

    private final VisitaServicioService visitaServicioService;

    @Autowired
    public VisitaServicioFunctions(VisitaServicioService visitaServicioService) {
        this.visitaServicioService = visitaServicioService;
    }

    /**
     * Obtener servicios por ID de visita
     */
    @FunctionName("obtenerServiciosPorVisita")
    public HttpResponseMessage obtenerServiciosPorVisita(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "visita-servicio/{idVisita}", authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        @BindingName("idVisita") String idVisitaStr,
        final ExecutionContext context) {

        context.getLogger().info("Obteniendo servicios para la visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            List<VisitaServicio> servicios = visitaServicioService.obtenerServiciosPorVisita(idVisita);
            return request.createResponseBuilder(HttpStatus.OK).body(servicios).build();
        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("El ID de la visita debe ser un número válido.")
                .build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    /**
     * Agregar persona a la visita
     */
    @FunctionName("agregarPersonaAVisita")
    public HttpResponseMessage agregarPersona(
        @HttpTrigger(name = "req", methods = {HttpMethod.POST}, route = "visita-servicio/{idVisita}/persona", authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<VisitaServicioDTO> request,
        @BindingName("idVisita") String idVisitaStr,
        final ExecutionContext context) {

        context.getLogger().info("Agregando persona a la visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            VisitaServicioDTO visitaDTO = request.getBody();

            if (visitaDTO == null) {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("El cuerpo de la solicitud no puede estar vacío.")
                    .build();
            }

            VisitaServicio nuevaPersona = visitaServicioService.agregarPersona(idVisita, visitaDTO);
            return request.createResponseBuilder(HttpStatus.CREATED).body(nuevaPersona).build();

        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("El ID de la visita debe ser un número válido.")
                .build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    /**
     * Actualizar persona en la visita
     */
    @FunctionName("actualizarPersonaEnVisita")
    public HttpResponseMessage actualizarPersona(
        @HttpTrigger(name = "req", methods = {HttpMethod.PUT}, route = "visita-servicio/{idVisita}/persona/{idVisitaServicio}", authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<VisitaServicioDTO> request,
        @BindingName("idVisita") String idVisitaStr,
        @BindingName("idVisitaServicio") String idVisitaServicioStr,
        final ExecutionContext context) {

        context.getLogger().info("Actualizando persona en la visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            Integer idVisitaServicio = Integer.parseInt(idVisitaServicioStr);
            VisitaServicioDTO visitaDTO = request.getBody();

            if (visitaDTO == null) {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("El cuerpo de la solicitud no puede estar vacío.")
                    .build();
            }

            VisitaServicio personaActualizada = visitaServicioService.editarPersona(idVisitaServicio, idVisita, visitaDTO);
            return request.createResponseBuilder(HttpStatus.OK).body(personaActualizada).build();

        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Los IDs deben ser números válidos.")
                .build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }

    /**
     * Eliminar persona de la visita
     */
    @FunctionName("eliminarPersonaDeVisita")
    public HttpResponseMessage eliminarPersona(
        @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "visita-servicio/{idVisita}/persona/{idVisitaServicio}", authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        @BindingName("idVisita") String idVisitaStr,
        @BindingName("idVisitaServicio") String idVisitaServicioStr,
        final ExecutionContext context) {

        context.getLogger().info("Eliminando persona con ID " + idVisitaServicioStr + " de la visita con ID: " + idVisitaStr);

        try {
            Integer idVisita = Integer.parseInt(idVisitaStr);
            Integer idVisitaServicio = Integer.parseInt(idVisitaServicioStr);
            visitaServicioService.eliminarPersona(idVisita, idVisitaServicio);
            return request.createResponseBuilder(HttpStatus.NO_CONTENT).build();

        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Los IDs deben ser números válidos.")
                .build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(request, e, context);
        }
    }
}
