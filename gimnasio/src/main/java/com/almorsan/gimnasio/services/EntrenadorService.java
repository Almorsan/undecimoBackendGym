
package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.EntrenadorDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrenadorService {
     @Autowired
     private EstablecimientoRepository establecimientoRepository;
     
     @Autowired
     private EntrenadorRepository entrenadorRepository;
    
    public Entrenador save(EntrenadorDTO entrenadorDTO) {
    Entrenador entrenador = new Entrenador();
    
    // Mapeo de campos simples
    entrenador.setNombre(entrenadorDTO.getNombre());
    entrenador.setNick(entrenadorDTO.getNick());
    entrenador.setContraseña(entrenadorDTO.getContraseña());
    entrenador.setEsAdmin(entrenadorDTO.getEsAdmin());
    entrenador.setFechaNacimiento(entrenadorDTO.getFechaNacimiento());
    entrenador.setFoto(entrenadorDTO.getFoto());
    entrenador.setFechaAlta(entrenadorDTO.getFechaAlta());
    entrenador.setFechaUltimoLogin(entrenadorDTO.getFechaUltimoLogin());

    // Obtener el establecimiento asociado por el ID
    Establecimiento establecimiento = establecimientoRepository.findById(entrenadorDTO.getEstablecimientoId())
        .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado con ID: " + entrenadorDTO.getEstablecimientoId()));
    entrenador.setEstablecimiento(establecimiento);

    // Obtener el creador (entrenador que lo creó) por ID
    Entrenador creador = entrenadorRepository.findById(entrenadorDTO.getCreadorId())
        .orElseThrow(() -> new RuntimeException("Creador no encontrado con ID: " + entrenadorDTO.getCreadorId()));
    entrenador.setCreador(creador);
    

    // Guardar el entrenador
    return entrenadorRepository.save(entrenador);
}
    
    public Entrenador update(Long id, EntrenadorDTO entrenadorDTO) {
    // Buscar el entrenador existente por ID
    Entrenador entrenadorExistente = entrenadorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con ID: " + id));

    // Actualizar los campos del entrenador existente con los datos proporcionados
    if (entrenadorDTO.getNombre() != null) {
        entrenadorExistente.setNombre(entrenadorDTO.getNombre());
    }
    if (entrenadorDTO.getNick() != null) {
        entrenadorExistente.setNick(entrenadorDTO.getNick());
    }
    if (entrenadorDTO.getContraseña() != null) {
        entrenadorExistente.setContraseña(entrenadorDTO.getContraseña());
    }
    if (entrenadorDTO.getEsAdmin() != null) {
        entrenadorExistente.setEsAdmin(entrenadorDTO.getEsAdmin());
    }
    if (entrenadorDTO.getFechaNacimiento() != null) {
        entrenadorExistente.setFechaNacimiento(entrenadorDTO.getFechaNacimiento());
    }
    if (entrenadorDTO.getFoto() != null) {
        entrenadorExistente.setFoto(entrenadorDTO.getFoto());
    }
    if (entrenadorDTO.getFechaAlta() != null) {
        entrenadorExistente.setFechaAlta(entrenadorDTO.getFechaAlta());
    }
    if (entrenadorDTO.getFechaUltimoLogin() != null) {
        entrenadorExistente.setFechaUltimoLogin(entrenadorDTO.getFechaUltimoLogin());
    }

    // Actualizar Establecimiento si se ha enviado un nuevo ID
    if (entrenadorDTO.getEstablecimientoId() != null) {
        Establecimiento establecimiento = establecimientoRepository.findById(entrenadorDTO.getEstablecimientoId())
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado con ID: " + entrenadorDTO.getEstablecimientoId()));
        entrenadorExistente.setEstablecimiento(establecimiento);
    }

    // Actualizar Creador si se ha enviado un nuevo ID
    if (entrenadorDTO.getCreadorId() != null) {
        Entrenador creador = entrenadorRepository.findById(entrenadorDTO.getCreadorId())
            .orElseThrow(() -> new RuntimeException("Creador no encontrado con ID: " + entrenadorDTO.getCreadorId()));
        entrenadorExistente.setCreador(creador);
    }

    // Guardar el entrenador actualizado
    return entrenadorRepository.save(entrenadorExistente);
}
    
    public List<Entrenador> getEntrenadoresByEstablecimiento(Long establecimientoId) {
        // Verificar que el establecimiento existe
        Establecimiento establecimiento = establecimientoRepository.findById(establecimientoId)
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));

        // Devolver la lista de entrenadores pertenecientes al establecimiento
        return entrenadorRepository.findByEstablecimiento(establecimiento);
    }

    
    

    
    
    
    
    
    // Método para verificar las credenciales
      // Método para verificar las credenciales
    public Entrenador authenticate(String nick, String password) {
        Entrenador entrenador = entrenadorRepository.findByNick(nick);
        if (entrenador != null && password.equals(entrenador.getContraseña())) {
            return entrenador;  // Si las contraseñas coinciden, retorna el entrenador
        }
        return null;  // Si no es válido, retorna null
    }
}

    

