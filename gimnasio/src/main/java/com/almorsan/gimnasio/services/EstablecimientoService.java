package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.EstablecimientoDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

   public Establecimiento save(EstablecimientoDTO establecimientoDTO) {
    Establecimiento establecimiento = new Establecimiento();

    // Establecer los datos básicos
    establecimiento.setDireccion(establecimientoDTO.getDireccion());
    establecimiento.setFoto(establecimientoDTO.getFoto());
    establecimiento.setFechaCreacionRegistro(establecimientoDTO.getFechaCreacionRegistro());

    // Buscar el entrenador creador por ID y asignarlo
    if (establecimientoDTO.getCreadorId() != null) {
        Entrenador creador = entrenadorRepository.findById(establecimientoDTO.getCreadorId())
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con ID: " + establecimientoDTO.getCreadorId()));
        establecimiento.setCreador(creador);
    }

    // Buscar el entrenador supervisor por ID y asignarlo si se proporciona
    if (establecimientoDTO.getEntrenadorSupervisorId() != null) {
        Entrenador entrenadorSupervisor = entrenadorRepository.findById(establecimientoDTO.getEntrenadorSupervisorId())
            .orElseThrow(() -> new RuntimeException("Entrenador supervisor no encontrado con ID: " + establecimientoDTO.getEntrenadorSupervisorId()));
        establecimiento.setEntrenadorSupervisor(entrenadorSupervisor);
    }

    // Guardar el establecimiento en la base de datos
    return establecimientoRepository.save(establecimiento);
}
   
  public Establecimiento update(Long id, EstablecimientoDTO establecimientoDTO) {
    // Buscar el establecimiento
    Optional<Establecimiento> establecimientoOpt = establecimientoRepository.findById(id);
    if (!establecimientoOpt.isPresent()) {
        throw new RuntimeException("Establecimiento no encontrado");
    }

    Establecimiento establecimiento = establecimientoOpt.get();

    // Verificar si se desea cambiar el entrenador supervisor
    if (establecimientoDTO.getEntrenadorSupervisorId() != null) {
        Long entrenadorSupervisorId = establecimientoDTO.getEntrenadorSupervisorId();
        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findById(entrenadorSupervisorId);

        if (!entrenadorOpt.isPresent()) {
            throw new RuntimeException("Entrenador Supervisor no encontrado");
        }

        Entrenador entrenadorSupervisor = entrenadorOpt.get();

        // Comprobar que el entrenador supervisor no esté asignado a otro establecimiento
        Optional<Establecimiento> otroEstablecimientoOpt = establecimientoRepository.findByEntrenadorSupervisor(entrenadorSupervisor);
        if (otroEstablecimientoOpt.isPresent() && !otroEstablecimientoOpt.get().getId().equals(establecimiento.getId())) {
            throw new RuntimeException("El entrenador supervisor ya está asignado a otro establecimiento");
        }

        // Verificar que el entrenador supervisor realmente trabaja en este establecimiento
        if (!establecimiento.getEntrenadores().contains(entrenadorSupervisor)) {
            throw new RuntimeException("El entrenador supervisor no trabaja en este establecimiento");
        }

        // Asignar el entrenador supervisor al establecimiento
        establecimiento.setEntrenadorSupervisor(entrenadorSupervisor);
    }

    // Actualizar otros campos si es necesario
    if (establecimientoDTO.getDireccion() != null) {
        establecimiento.setDireccion(establecimientoDTO.getDireccion());
    }

    if (establecimientoDTO.getFoto() != null) {
        establecimiento.setFoto(establecimientoDTO.getFoto());
    }

    // Actualizar el ID del creador si se proporciona (si el creador puede cambiar)
    if (establecimientoDTO.getCreadorId() != null) {
        Optional<Entrenador> creadorOpt = entrenadorRepository.findById(establecimientoDTO.getCreadorId());
        if (!creadorOpt.isPresent()) {
            throw new RuntimeException("Creador no encontrado");
        }
        establecimiento.setCreador(creadorOpt.get());
    }

    // Guardar el establecimiento actualizado
    return establecimientoRepository.save(establecimiento);
}
}
