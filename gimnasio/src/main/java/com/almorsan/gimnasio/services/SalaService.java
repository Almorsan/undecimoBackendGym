

package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.SalaDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import com.almorsan.gimnasio.repositories.SalaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    public Sala save(SalaDTO salaDTO) {
        Sala sala = new Sala();
        sala.setNombre(salaDTO.getNombre());
        sala.setFoto(salaDTO.getFoto());
        sala.setFechaCreacionRegistro(salaDTO.getFechaCreacionRegistro());

        // Buscar el establecimiento por ID
        Establecimiento establecimiento = establecimientoRepository.findById(salaDTO.getEstablecimientoId())
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado con ID: " + salaDTO.getEstablecimientoId()));
        sala.setEstablecimiento(establecimiento);

        // Buscar el entrenador supervisor por ID
        if (salaDTO.getEntrenadorSupervisorId() != null) {
            Entrenador entrenadorSupervisor = entrenadorRepository.findById(salaDTO.getEntrenadorSupervisorId())
                .orElseThrow(() -> new RuntimeException("Entrenador supervisor no encontrado con ID: " + salaDTO.getEntrenadorSupervisorId()));
            sala.setEntrenadorSupervisor(entrenadorSupervisor);
        }

        // Buscar el creador por ID
        if (salaDTO.getCreadorId() != null) {
            Entrenador creador = entrenadorRepository.findById(salaDTO.getCreadorId())
                .orElseThrow(() -> new RuntimeException("Entrenador creador no encontrado con ID: " + salaDTO.getCreadorId()));
            sala.setCreador(creador);
        }

        return salaRepository.save(sala);
    }
    
    
    public Sala actualizarEntrenadorSupervisor(Long salaId, Long entrenadorId) {
    // Verificar si la sala existe
    Sala sala = salaRepository.findById(salaId)
        .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

    // Verificar si el entrenador existe
    Entrenador entrenadorSupervisor = entrenadorRepository.findById(entrenadorId)
        .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

    // Verificar que el entrenador pertenece al mismo establecimiento que la sala
    if (!entrenadorSupervisor.getEstablecimiento().getId().equals(sala.getEstablecimiento().getId())) {
        throw new RuntimeException("El entrenador no pertenece al mismo gimnasio que la sala");
    }

    // Actualizar el entrenador supervisor de la sala
    sala.setEntrenadorSupervisor(entrenadorSupervisor);

    return salaRepository.save(sala);
}
   public Sala actualizarSala(Long salaId, SalaDTO salaDTO) {
    // Verificar si la sala existe
    Sala sala = salaRepository.findById(salaId)
        .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

    // Actualizar el nombre de la sala, si es proporcionado
    if (salaDTO.getNombre() != null) {
        sala.setNombre(salaDTO.getNombre());
    }

    // Actualizar el entrenador supervisor, si es proporcionado
    if (salaDTO.getEntrenadorSupervisorId() != null) {
        Entrenador entrenadorSupervisor = entrenadorRepository.findById(salaDTO.getEntrenadorSupervisorId())
            .orElseThrow(() -> new RuntimeException("Entrenador supervisor no encontrado"));

        // Verificar que el entrenador pertenece al mismo establecimiento que la sala
        if (!entrenadorSupervisor.getEstablecimiento().getId().equals(sala.getEstablecimiento().getId())) {
            throw new RuntimeException("El entrenador no pertenece al mismo gimnasio que la sala");
        }

        sala.setEntrenadorSupervisor(entrenadorSupervisor);
    }

    // Actualizar otros campos de la sala, como la foto y la fecha
    if (salaDTO.getFoto() != null) {
        sala.setFoto(salaDTO.getFoto());
    }
    if (salaDTO.getFechaCreacionRegistro() != null) {
        sala.setFechaCreacionRegistro(salaDTO.getFechaCreacionRegistro());
    }

    // Guardar los cambios
    return salaRepository.save(sala);
}
   
   
   public List<Sala> getSalasByEstablecimiento(Long establecimientoId) {
        // Verificar que el establecimiento existe
        Establecimiento establecimiento = establecimientoRepository.findById(establecimientoId)
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));

        // Devolver la lista de salas pertenecientes al establecimiento
        return salaRepository.findByEstablecimiento(establecimiento);
    }
}

