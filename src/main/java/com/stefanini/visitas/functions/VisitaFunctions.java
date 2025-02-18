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

	@FunctionName("obtenerVisitas")
	public HttpResponseMessage obtenerTodasLasVisitas(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {

		context.getLogger().info("Obteniendo todas las visitas");

		return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "application/json").body(new ErrorResponse(200,"Hola Mundo"))
				.build();
	}

	
}