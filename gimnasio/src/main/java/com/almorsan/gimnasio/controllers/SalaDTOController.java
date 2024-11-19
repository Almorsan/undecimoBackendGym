
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.dtos.EntrenadorDTO;
import com.almorsan.gimnasio.dtos.SalaDTO;
import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.SalaRepository;
import com.almorsan.gimnasio.services.SalaEntrenadorService;
import com.almorsan.gimnasio.services.SalaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salas")
public class SalaDTOController {

    @Autowired
    private SalaService salaService;
    
    @Autowired
    private ActividadRepository actividadRepository;
    
      @Autowired
    private SalaRepository salaRepository;

    @PostMapping
    public Sala crearSala(@RequestBody SalaDTO salaDTO) {
        return salaService.save(salaDTO);
    }
    
     @Autowired
    private SalaEntrenadorService salaEntrenadorService;

    @PostMapping("/{salaId}/entrenadores/{entrenadorId}")
    public Sala addEntrenadorToSala(@PathVariable Long salaId, @PathVariable Long entrenadorId) {
        return salaEntrenadorService.addEntrenadorToSala(salaId, entrenadorId);
    }
    
    
@PatchMapping("/{salaId}")
public ResponseEntity<Sala> actualizarSala(
        @PathVariable Long salaId,
        @RequestBody SalaDTO salaDTO) {
    
    // Llamamos al servicio para actualizar los datos de la sala
    Sala salaActualizada = salaService.actualizarSala(salaId, salaDTO);
    return ResponseEntity.ok(salaActualizada);
}


 @GetMapping("/establecimiento/{establecimientoId}")
    public ResponseEntity<List<Sala>> getSalasByEstablecimiento(@PathVariable Long establecimientoId) {
        List<Sala> salas = salaService.getSalasByEstablecimiento(establecimientoId);
        return ResponseEntity.ok(salas);
    }
    
    
@CrossOrigin
@GetMapping("/bySala/{salaId}")
public ResponseEntity<List<Actividad>> listarActividadesPorSala(@PathVariable Long salaId) {
    // Suponiendo que tienes un repositorio de Sala para obtener la sala por su ID
    Optional<Sala> salaOptional = salaRepository.findById(salaId);
    
    // Si no se encuentra la sala, responde con un 404 Not Found
    if (salaOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    // Si la sala existe, se pasa al método findBySala del repositorio de Actividad
    List<Actividad> actividades = actividadRepository.findBySala(salaOptional.get());
    
    // Si no hay actividades asociadas a la sala, también se responde con un 404 Not Found
    if (actividades.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    // Si hay actividades, se devuelven en la respuesta con un código 200 OK
    return ResponseEntity.ok(actividades);
}
    
    
    
    
    
    
    
    
}




