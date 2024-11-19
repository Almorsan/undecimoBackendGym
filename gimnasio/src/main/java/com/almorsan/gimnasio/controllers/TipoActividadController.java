
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.TipoActividad;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.TipoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/tipoActividades")
public class TipoActividadController {
    
    @Autowired
    private TipoActividadRepository tipoActividadRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<TipoActividad> listarTipoActividades() {
        return tipoActividadRepository.findAll();
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<TipoActividad> encontrarTipoActividadrPorId(@PathVariable Long id) {
        Optional<TipoActividad> tipoActividad = tipoActividadRepository.findById(id);

        return tipoActividad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @CrossOrigin
    @PostMapping
    public ResponseEntity<TipoActividad> crearTipoActividad(@RequestBody TipoActividad tipoActividad) {
        TipoActividad tipoActividadGuardada = tipoActividadRepository.save(tipoActividad);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoActividadGuardada);

    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTipoActiviad(@PathVariable Long id) {
        if (!tipoActividadRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        tipoActividadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
}
