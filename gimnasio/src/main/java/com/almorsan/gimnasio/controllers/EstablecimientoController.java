
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {
    
    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<Establecimiento> listarEstablecimientos() {
        return establecimientoRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Establecimiento> encontrarEstablecimientoPorId(@PathVariable Long id) {
        Optional<Establecimiento> establecimiento = establecimientoRepository.findById(id);

        return establecimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Establecimiento> crearEstablecimiento(@RequestBody Establecimiento establecimiento) {
        Establecimiento establecimientoGuardado = establecimientoRepository.save(establecimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(establecimientoGuardado);

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEstablecimiento(@PathVariable Long id) {
        if (!establecimientoRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        establecimientoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
     @CrossOrigin
@PatchMapping("/{id}")
public ResponseEntity<Establecimiento> actualizarEstablecimiento(@PathVariable Long id, @RequestBody Establecimiento establecimiento) {
    Optional<Establecimiento> establecimientoOptional = establecimientoRepository.findById(id);

    if (establecimientoOptional.isPresent()) {
        Establecimiento establiecimientoExistente = establecimientoOptional.get();

        
        if (establecimiento.getDireccion() != null) {
            establiecimientoExistente.setDireccion(establecimiento.getDireccion());
        }
        
       
       

        Establecimiento establecimientoActualizado = establecimientoRepository.save(establiecimientoExistente);
        return ResponseEntity.ok(establecimientoActualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}
}
