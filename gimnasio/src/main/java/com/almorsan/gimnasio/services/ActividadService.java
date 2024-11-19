package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.ActividadDTO;
import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.models.TipoActividad;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import com.almorsan.gimnasio.repositories.SalaRepository;
import com.almorsan.gimnasio.repositories.TipoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private TipoActividadRepository tipoActividadRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private SalaRepository salaRepository;

    // Método para verificar si las actividades se solapan en la misma sala
    private boolean verificarSolapamiento(Sala sala, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Actividad> actividadesEnSala = actividadRepository.findBySala(sala);

        for (Actividad actividad : actividadesEnSala) {
            // Comprobar si las fechas se solapan
            boolean solapamiento = (fechaInicio.isBefore(actividad.getFechaFin()) && fechaFin.isAfter(actividad.getFechaInicio()));
            if (solapamiento) {
                return true;  // Si hay solapamiento, retorna true
            }
        }

        return false;  // Si no hay solapamiento, retorna false
    }

    public Actividad save(ActividadDTO actividadDTO) {
        // Comprobar si el entrenador pertenece al establecimiento
        Entrenador entrenador = entrenadorRepository.findById(actividadDTO.getEntrenadorId())
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
        
        Establecimiento establecimiento = establecimientoRepository.findById(actividadDTO.getEstablecimientoId())
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));

        // Validar que el entrenador está asignado al establecimiento
        if (!entrenador.getEstablecimiento().getId().equals(actividadDTO.getEstablecimientoId())) {
            throw new RuntimeException("El entrenador no está asignado a este establecimiento");
        }

        // Comprobar si la sala pertenece al establecimiento
        Sala sala = salaRepository.findById(actividadDTO.getSalaId())
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        // Validar que la sala pertenece al establecimiento
        if (!sala.getEstablecimiento().getId().equals(actividadDTO.getEstablecimientoId())) {
            throw new RuntimeException("La sala no pertenece a este establecimiento");
        }

        // Verificar si las actividades se solapan en la misma sala
        if (verificarSolapamiento(sala, actividadDTO.getFechaInicio(), actividadDTO.getFechaFin())) {
            throw new RuntimeException("Las actividades se solapan en la misma sala");
        }

        // Crear y guardar la nueva actividad
        Actividad actividad = new Actividad();
        actividad.setFechaInicio(actividadDTO.getFechaInicio());
        actividad.setFechaFin(actividadDTO.getFechaFin());
        actividad.setFechaCreacionRegistro(actividadDTO.getFechaCreacionRegistro());

        // Asignar los valores del DTO al modelo de Actividad
        TipoActividad tipoActividad = new TipoActividad();
        tipoActividad.setId(actividadDTO.getTipoActividadId());
        actividad.setTipoActividad(tipoActividad);

        Entrenador creador = new Entrenador();
        creador.setId(actividadDTO.getEntrenadorId());
        actividad.setEntrenador(creador);

        Establecimiento establecimientoAsignado = new Establecimiento();
        establecimientoAsignado.setId(actividadDTO.getEstablecimientoId());
        actividad.setEstablecimiento(establecimientoAsignado);

        Sala salaAsignada = new Sala();
        salaAsignada.setId(actividadDTO.getSalaId());
        actividad.setSala(salaAsignada);

        Entrenador registroCreadoPor = entrenadorRepository.findById(actividadDTO.getRegistroCreadoPorId())
            .orElseThrow(() -> new RuntimeException("Entrenador creador no encontrado"));
        actividad.setRegistroCreadoPor(registroCreadoPor);

        // Guardar la actividad en la base de datos
        return actividadRepository.save(actividad);
    }
    
    public Actividad actualizarActividad(Long actividadId, ActividadDTO actividadDTO) {
    // Obtener la actividad existente
    Actividad actividad = actividadRepository.findById(actividadId)
        .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

    // Validar el entrenador
    Entrenador entrenador = entrenadorRepository.findById(actividadDTO.getEntrenadorId())
        .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

    Establecimiento establecimiento = establecimientoRepository.findById(actividadDTO.getEstablecimientoId())
        .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));

    if (!entrenador.getEstablecimiento().getId().equals(actividadDTO.getEstablecimientoId())) {
        throw new RuntimeException("El entrenador no está asignado a este establecimiento");
    }

    // Validar la sala
    Sala sala = salaRepository.findById(actividadDTO.getSalaId())
        .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

    if (!sala.getEstablecimiento().getId().equals(actividadDTO.getEstablecimientoId())) {
        throw new RuntimeException("La sala no pertenece a este establecimiento");
    }

    // Verificar solapamiento de actividades en la misma sala
    if (verificarSolapamiento(sala, actividadDTO.getFechaInicio(), actividadDTO.getFechaFin())) {
        throw new RuntimeException("Las actividades se solapan en la misma sala");
    }

    // Actualizar solo los campos proporcionados
    if (actividadDTO.getFechaInicio() != null) {
        actividad.setFechaInicio(actividadDTO.getFechaInicio());
    }
    if (actividadDTO.getFechaFin() != null) {
        actividad.setFechaFin(actividadDTO.getFechaFin());
    }
    if (actividadDTO.getTipoActividadId() != null) {
        TipoActividad tipoActividad = new TipoActividad();
        tipoActividad.setId(actividadDTO.getTipoActividadId());
        actividad.setTipoActividad(tipoActividad);
    }
    if (actividadDTO.getEntrenadorId() != null) {
        actividad.setEntrenador(entrenador);
    }
    if (actividadDTO.getEstablecimientoId() != null) {
        actividad.setEstablecimiento(establecimiento);
    }
    if (actividadDTO.getSalaId() != null) {
        actividad.setSala(sala);
    }
    if (actividadDTO.getRegistroCreadoPorId() != null) {
        Entrenador registroCreadoPor = entrenadorRepository.findById(actividadDTO.getRegistroCreadoPorId())
            .orElseThrow(() -> new RuntimeException("Entrenador creador no encontrado"));
        actividad.setRegistroCreadoPor(registroCreadoPor);
    }

    // Guardar la actividad actualizada en la base de datos
    return actividadRepository.save(actividad);
}
}
