package com.jeison.apivideojuegos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jeison.apivideojuegos.dto.VideoJuegoDto;
import com.jeison.apivideojuegos.model.VideoJuego;
import com.jeison.apivideojuegos.model.Genero;
import com.jeison.apivideojuegos.repository.VideojuegoRepository;
import com.jeison.apivideojuegos.repository.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.jeison.apivideojuegos.service.ApiExternaService;

import java.util.List;

@RestController
public class VideojuegoController {

    private final VideojuegoRepository repository;
    private final GeneroRepository generoRepository;
    private final ApiExternaService apiExternaService;

    public VideojuegoController(
        VideojuegoRepository repository,
        GeneroRepository generoRepository,
        ApiExternaService apiExternaService) {

        this.repository = repository;
        this.generoRepository = generoRepository;
        this.apiExternaService = apiExternaService;
    }

    @GetMapping("/videojuegos")
    public List<VideoJuego> obtenerVideojuegos() {
        return repository.findAll();
    }

    @GetMapping("/videojuegos/{id}")
    public ResponseEntity<VideoJuego> obtenerVideojuegoPorId(@PathVariable int id) {

    return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
     }


    @GetMapping("/videojuegos/buscar")
    public List<VideoJuego> buscarPorGenero(@RequestParam String genero) {

        return repository.findByGeneroNombreIgnoreCase(genero);
    }



    @PostMapping("/videojuegos")
    public ResponseEntity<VideoJuego> agregarVideojuego(@RequestBody VideoJuegoDto dto) {

        Genero genero = generoRepository.findById(dto.generoId())
                .orElse(null);

        if (genero == null) {
            return ResponseEntity.notFound().build();
        }

        VideoJuego nuevoVideojuego = new VideoJuego(
                0,
                dto.nombre(),
                genero,
                dto.plataforma()
    );

    VideoJuego guardado = repository.save(nuevoVideojuego);

    return ResponseEntity.status(201).body(guardado);
    }

    @PutMapping("/videojuegos/{id}")
    public ResponseEntity<VideoJuego> actualizarVideojuego(
            @PathVariable int id,
            @RequestBody VideoJuegoDto dto) {

        var videojuegoOptional = repository.findById(id);

        if (videojuegoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Genero genero = generoRepository.findById(dto.generoId())
                .orElse(null);

        if (genero == null) {
            return ResponseEntity.notFound().build();
        }

        VideoJuego videojuego = videojuegoOptional.get();

        videojuego.setNombre(dto.nombre());
        videojuego.setGenero(genero);
        videojuego.setPlataforma(dto.plataforma());

        VideoJuego actualizado = repository.save(videojuego);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/videojuegos/{id}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable int id) {

        if (!repository.existsById(id)) {
        return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api-externa")
    public ResponseEntity<String> consultarApiExterna() {
        return ResponseEntity.ok(apiExternaService.consultarApiExterna());
    }


}