
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.services.ClienteActividadService;
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

@RestController
@RequestMapping("/actividades")
public class ActividadController {
    
    @Autowired
    private ActividadRepository actividadRepository;
    
     @Autowired
    private ClienteActividadService clienteActividadService;

    @CrossOrigin
    @GetMapping("/all")
    public List<Actividad> listarActividades() {
        return actividadRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Actividad> encontrarActividadPorId(@PathVariable Long id) {
        Optional<Actividad> actividad = actividadRepository.findById(id);

        return actividad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Actividad> crearEntrenador(@RequestBody Actividad actividad) {
        Actividad actividadGuardada = actividadRepository.save(actividad);
        return ResponseEntity.status(HttpStatus.CREATED).body(actividadGuardada);

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarActividad (@PathVariable Long id) {
        if (!actividadRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        actividadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{clienteId}/actividad/{actividadId}")
    public ResponseEntity<String> agregarClienteAActividad(@PathVariable Long clienteId, @PathVariable Long actividadId) {
        try {
            String respuesta = clienteActividadService.agregarClienteAActividad(clienteId, actividadId);
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
