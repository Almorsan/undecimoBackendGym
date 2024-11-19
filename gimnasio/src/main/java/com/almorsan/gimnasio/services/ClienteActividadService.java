/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Cliente;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.models.Sala;
import com.almorsan.gimnasio.repositories.ActividadRepository;
import com.almorsan.gimnasio.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anasa
 */
@Service
public class ClienteActividadService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

   // @Autowired
  //  private ClientesActividadesRepository clientesActividadesRepository;

    /*
   public String agregarClienteAActividad(Long clienteId, Long actividadId) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Actividad actividad = actividadRepository.findById(actividadId)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

    // Verificar si el cliente ya está inscrito en otra actividad
    for (Actividad actividadExistente : cliente.getActividades()) {
        if (esSolapamiento(actividad, actividadExistente)) {
            throw new RuntimeException("El cliente ya está inscrito en una actividad con horarios solapados");
        }
    }

    // Agregar cliente a la actividad
    cliente.getActividades().add(actividad);
    actividad.getClientes().add(cliente);

    // Obtener el entrenador de la actividad (suponiendo que cada actividad tiene un único entrenador)
    Entrenador entrenador = actividad.getEntrenador();  // Esto depende de cómo esté modelado el entrenador en la actividad

    // Agregar la relación cliente-entrenador
    if (entrenador != null) {
        cliente.getEntrenadores().add(entrenador);
        entrenador.getClientes().add(cliente);
    }

    // Guardar en la base de datos
    clienteRepository.save(cliente);
    actividadRepository.save(actividad);

    return "Cliente agregado a la actividad correctamente";
}
    */
    
    public String agregarClienteAActividad(Long clienteId, Long actividadId) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Actividad actividad = actividadRepository.findById(actividadId)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

    // Verificar si el cliente ya está inscrito en otra actividad
    for (Actividad actividadExistente : cliente.getActividades()) {
        if (esSolapamiento(actividad, actividadExistente)) {
            throw new RuntimeException("El cliente ya está inscrito en una actividad con horarios solapados");
        }
    }

    // Agregar cliente a la actividad
    cliente.getActividades().add(actividad);
    actividad.getClientes().add(cliente);

    // Gestionar la relación cliente-establecimiento
    Establecimiento establecimiento = actividad.getEstablecimiento();
    if (establecimiento != null && !cliente.getEstablecimientos().contains(establecimiento)) {
        cliente.getEstablecimientos().add(establecimiento);
        establecimiento.getClientes().add(cliente);
    }

    // Gestionar la relación cliente-entrenador
    Entrenador entrenador = actividad.getEntrenador();
    if (entrenador != null && !cliente.getEntrenadores().contains(entrenador)) {
        cliente.getEntrenadores().add(entrenador);
        entrenador.getClientes().add(cliente);
    }

    // Gestionar la relación salas-entrenadores
    Sala sala = actividad.getSala();
    if (sala != null && entrenador != null && !sala.getEntrenadores().contains(entrenador)) {
        sala.getEntrenadores().add(entrenador);
        entrenador.getSalas().add(sala);
    }

    // Guardar en la base de datos
    clienteRepository.save(cliente);
    actividadRepository.save(actividad);

    return "Cliente agregado a la actividad correctamente";
}
 
   public String eliminarClienteDeActividad(Long clienteId, Long actividadId) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Actividad actividad = actividadRepository.findById(actividadId)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

    // Verificar si el cliente está inscrito en la actividad
    if (!cliente.getActividades().contains(actividad)) {
        throw new RuntimeException("El cliente no está inscrito en la actividad");
    }

    // Eliminar la relación cliente-actividad
    cliente.getActividades().remove(actividad);
    actividad.getClientes().remove(cliente);

    // Gestionar la relación cliente-establecimiento
    Establecimiento establecimiento = actividad.getEstablecimiento();
    if (establecimiento != null) {
        boolean estaEnOtrasActividadesDelEstablecimiento = cliente.getActividades().stream()
                .anyMatch(actividadExistente -> actividadExistente.getEstablecimiento() != null
                        && actividadExistente.getEstablecimiento().equals(establecimiento));

        if (!estaEnOtrasActividadesDelEstablecimiento) {
            cliente.getEstablecimientos().remove(establecimiento);
            establecimiento.getClientes().remove(cliente);
        }
    }

    // Gestionar la relación cliente-entrenador
    Entrenador entrenador = actividad.getEntrenador();
    if (entrenador != null) {
        boolean estaEnOtrasActividadesConEntrenador = cliente.getActividades().stream()
                .anyMatch(actividadExistente -> actividadExistente.getEntrenador() != null
                        && actividadExistente.getEntrenador().equals(entrenador));

        if (!estaEnOtrasActividadesConEntrenador) {
            cliente.getEntrenadores().remove(entrenador);
            entrenador.getClientes().remove(cliente);
        }
    }

    // Gestionar la relación salas-entrenadores
    Sala sala = actividad.getSala();
    if (sala != null && entrenador != null) {
        boolean entrenadorRelacionadoConOtrasActividadesDeLaSala = cliente.getActividades().stream()
                .anyMatch(actividadExistente -> actividadExistente.getSala() != null
                        && actividadExistente.getSala().equals(sala)
                        && actividadExistente.getEntrenador() != null
                        && actividadExistente.getEntrenador().equals(entrenador));

        if (!entrenadorRelacionadoConOtrasActividadesDeLaSala) {
            sala.getEntrenadores().remove(entrenador);
            entrenador.getSalas().remove(sala);
        }
    }

    // Guardar en la base de datos
    clienteRepository.save(cliente);
    actividadRepository.save(actividad);

    return "Cliente eliminado de la actividad correctamente";
}
   
   
    private boolean esSolapamiento(Actividad nuevaActividad, Actividad actividadExistente) {
        // Verificar si los horarios se solapan
        return !(nuevaActividad.getFechaFin().isBefore(actividadExistente.getFechaInicio()) ||
                 nuevaActividad.getFechaInicio().isAfter(actividadExistente.getFechaFin()));
    }
}