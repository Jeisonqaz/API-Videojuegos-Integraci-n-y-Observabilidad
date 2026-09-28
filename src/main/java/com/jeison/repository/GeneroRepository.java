package com.jeison.apivideojuegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jeison.apivideojuegos.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}