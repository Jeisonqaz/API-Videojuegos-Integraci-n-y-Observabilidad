package com.jeison.apivideojuegos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApiExternaService {

    private final RestClient restClient;
    private final MeterRegistry meterRegistry;
    private static final Logger logger = LoggerFactory.getLogger(ApiExternaService.class);

    public ApiExternaService(MeterRegistry meterRegistry) {
        this.restClient = RestClient.create();
        this.meterRegistry = meterRegistry;
    }

    public String consultarApiExterna() {

        meterRegistry.counter("api.externa.consultas").increment();

        logger.info("Iniciando consulta a la API externa JSONPlaceholder");

        try {
            String respuesta = restClient.get()
                 .uri("https://jsonplaceholder.typicode.com/todos/1")
                 .retrieve()
                 .body(String.class);

            logger.info("Consulta a la API externa realizada correctamente");

        return respuesta;

        }   catch (Exception e) {

            logger.warn("La API externa presentó un problema: {}", e.getMessage());
            logger.error("Error al consultar la API externa", e);

        return "Error al consultar la API externa: " + e.getMessage();
        }
    }
}