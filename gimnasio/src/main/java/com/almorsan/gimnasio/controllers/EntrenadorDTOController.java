

package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.dtos.EntrenadorDTO;
import com.almorsan.gimnasio.dtos.EstablecimientoDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.services.EntrenadorService;
import com.almorsan.gimnasio.models.Establecimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorDTOController {

    @Autowired
    private EntrenadorService entrenadorService;


@PostMapping
    public Entrenador crearEntrenador (@RequestBody EntrenadorDTO entrenadorDTO) {
        return entrenadorService.save(entrenadorDTO); 
    }
    


  

    @PatchMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(@PathVariable Long id, @RequestBody EntrenadorDTO entrenadorDTO) {
        System.out.println("Datos recibidos en PATCH: " + entrenadorDTO);
        try {
            Entrenador entrenadorActualizado = entrenadorService.update(id, entrenadorDTO);
            return ResponseEntity.ok(entrenadorActualizado);  // Devuelve el entrenador actualizado
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Si no se encuentra el entrenador, responde con un error
        }
    }
    
      @GetMapping("/establecimiento/{establecimientoId}")
    public ResponseEntity<List<Entrenador>> getEntrenadoresByEstablecimiento(@PathVariable Long establecimientoId) {
        List<Entrenador> entrenadores = entrenadorService.getEntrenadoresByEstablecimiento(establecimientoId);
        return ResponseEntity.ok(entrenadores);
    }
}

  

  




   


