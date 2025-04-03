package mx.unam.dgtic.videojuegosapi.controller;

import jakarta.validation.Valid;
import mx.unam.dgtic.videojuegosapi.model.Videojuego;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final HashMap<Long, Videojuego> videojuego = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    VideojuegoController() {
        Videojuego v1 = new Videojuego(idGenerator.getAndIncrement(), "The Legend of Zelda: Breath of the Wild", "Nintendo Switch", "Aventura");
        Videojuego v2 = new Videojuego(idGenerator.getAndIncrement(), "God of War: Ragnarok", "PS5", "Acción");
        Videojuego v3 = new Videojuego(idGenerator.getAndIncrement(), "Halo Infinite", "Xbox Series X", "Shooter");
        Videojuego v4 = new Videojuego(idGenerator.getAndIncrement(), "Minecraft", "PC", "Sandbox");
        Videojuego v5 = new Videojuego(idGenerator.getAndIncrement(), "Final Fantasy VII Remake", "PS4", "RPG");


        videojuego.put(v1.getId(), v1);
        videojuego.put(v2.getId(), v2);
        videojuego.put(v3.getId(), v3);
        videojuego.put(v4.getId(), v4);
        videojuego.put(v5.getId(), v5);
    }

    @GetMapping("/")
    public ResponseEntity<List<Videojuego>> obtenerTodos() {
        List<Videojuego> lista = new ArrayList<>(videojuego.values());
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        return Optional.ofNullable(videojuego.get(id))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/")
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody Videojuego nuevo) {
        long nuevoId = idGenerator.getAndIncrement();
        nuevo.setId(nuevoId);
        videojuego.put(nuevoId, nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizarCompleto(@PathVariable Long id, @Valid @RequestBody Videojuego nuevo) {
        if (!videojuego.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        nuevo.setId(id);
        videojuego.put(id, nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Videojuego> actualizarParcial(@PathVariable Long id, @RequestBody Videojuego cambios) {
        Videojuego existente = videojuego.get(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        if (cambios.getTitulo() != null) existente.setTitulo(cambios.getTitulo());
        if (cambios.getPlataforma() != null) existente.setPlataforma(cambios.getPlataforma());
        if (cambios.getGenero() != null) existente.setGenero(cambios.getGenero());

        videojuego.put(id, existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!videojuego.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        videojuego.remove(id);
        return ResponseEntity.noContent().build();
    }



}
