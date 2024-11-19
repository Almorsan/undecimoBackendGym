package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.SalaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<Sala> listasSalas() {
        return salaRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Sala> encontrarSalaPorId(@PathVariable Long id) {
        Optional<Sala> sala = salaRepository.findById(id);

        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Sala> crearSala(@RequestBody Sala sala) {
        Sala salaGuardada = salaRepository.save(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(salaGuardada);

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarSala(@PathVariable Long id) {
        if (!salaRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        salaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
