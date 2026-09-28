package com.jeison.apivideojuegos.dto;

public record VideoJuegoDto(
    String nombre,
    int generoId,
    String plataforma
    ) {
}