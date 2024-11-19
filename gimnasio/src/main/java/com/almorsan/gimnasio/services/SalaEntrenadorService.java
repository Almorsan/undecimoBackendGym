
package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.SalaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SalaEntrenadorService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    
   // private SalaEntrenadorRepository salaEntrenadorRepository;

   public Sala addEntrenadorToSala(Long salaId, Long entrenadorId) {
    // Verificar si la sala existe
    Optional<Sala> salaOpt = salaRepository.findById(salaId);
    if (!salaOpt.isPresent()) {
        throw new RuntimeException("Sala no encontrada");
    }
    Sala sala = salaOpt.get();

    // Verificar si el entrenador existe
    Optional<Entrenador> entrenadorOpt = entrenadorRepository.findById(entrenadorId);
    if (!entrenadorOpt.isPresent()) {
        throw new RuntimeException("Entrenador no encontrado");
    }
    Entrenador entrenador = entrenadorOpt.get();

    // Verificar que el entrenador trabaja en el establecimiento de la sala
    if (!entrenador.getEstablecimiento().getId().equals(sala.getEstablecimiento().getId())) {
        throw new RuntimeException("El entrenador no trabaja en el establecimiento de esta sala");
    }

    // Verificar si el entrenador ya está asignado a la sala
    if (entrenador.getSalas().contains(sala)) {
        throw new RuntimeException("El entrenador ya está asignado a esta sala");
    }

    // Agregar el entrenador a la sala
    entrenador.getSalas().add(sala);

    // Guardar el entrenador con la relación actualizada
    entrenadorRepository.save(entrenador);

    // Ahora devolvemos la sala actualizada con el entrenador agregado
    return sala;
}
}
