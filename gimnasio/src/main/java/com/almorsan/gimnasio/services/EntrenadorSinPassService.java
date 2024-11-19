

package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.EntrenadorDTO;
import com.almorsan.gimnasio.dtos.EntrenadorSinPassDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.repositories.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrenadorSinPassService {
     @Autowired
     private EstablecimientoRepository establecimientoRepository;
     
     @Autowired
     private EntrenadorRepository entrenadorRepository;
    
    public Entrenador save(EntrenadorSinPassDTO entrenadorSinPassDTO) {
    Entrenador entrenador = new Entrenador();
    
    // Mapeo de campos simples
    entrenador.setNombre(entrenadorSinPassDTO.getNombre());
    entrenador.setNick(entrenadorSinPassDTO.getNick());
    entrenador.setEsAdmin(entrenadorSinPassDTO.getEsAdmin());
    entrenador.setFechaNacimiento(entrenadorSinPassDTO.getFechaNacimiento());
    entrenador.setFoto(entrenadorSinPassDTO.getFoto());
    entrenador.setFechaAlta(entrenadorSinPassDTO.getFechaAlta());
    entrenador.setFechaUltimoLogin(entrenadorSinPassDTO.getFechaUltimoLogin());
   

    // Obtener el establecimiento asociado por el ID
    Establecimiento establecimiento = establecimientoRepository.findById(entrenadorSinPassDTO.getEstablecimientoId())
        .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado con ID: " + entrenadorSinPassDTO.getEstablecimientoId()));
    entrenador.setEstablecimiento(establecimiento);

    // Obtener el creador (entrenador que lo creó) por ID
    Entrenador creador = entrenadorRepository.findById(entrenadorSinPassDTO.getCreadorId())
        .orElseThrow(() -> new RuntimeException("Creador no encontrado con ID: " + entrenadorSinPassDTO.getCreadorId()));
    entrenador.setCreador(creador);
    
    

    // Guardar el entrenador
    return entrenadorRepository.save(entrenador);
}
    
    public Entrenador findByNick(String nick) {
    return entrenadorRepository.findByNick(nick);
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

    


