package com.jeison.apivideojuegos.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApiExternaHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("apiExterna", "Disponible")
                .withDetail("servicio", "JSONPlaceholder")
                .build();
    }
}