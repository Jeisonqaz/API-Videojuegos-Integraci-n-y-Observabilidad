package com.jeison.apivideojuegos.repository;

import com.jeison.apivideojuegos.model.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<VideoJuego, Integer> {

    List<VideoJuego> findByGeneroNombreIgnoreCase(String nombre);

}