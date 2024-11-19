
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.dtos.EntrenadorDTO;
import com.almorsan.gimnasio.dtos.EntrenadorSinPassDTO;
import com.almorsan.gimnasio.dtos.EstablecimientoDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.services.EntrenadorService;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.services.EntrenadorSinPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadoresSinPass")
public class EntrenadorSinPassController {

    @Autowired
    private EntrenadorSinPassService entrenadorSinPassService;


@PostMapping
    public Entrenador crearEntrenador (@RequestBody EntrenadorSinPassDTO entrenadorSinPassDTO) {
        return entrenadorSinPassService.save(entrenadorSinPassDTO); 
    }
    
    


@GetMapping("/{nick}")
public ResponseEntity<EntrenadorSinPassDTO> getEntrenadorByNick(@PathVariable String nick) {
    Entrenador entrenador = entrenadorSinPassService.findByNick(nick);
    if (entrenador != null) {
        // Crear un DTO sin la contraseña
        EntrenadorSinPassDTO dto = new EntrenadorSinPassDTO();
        dto.setNombre(entrenador.getNombre());
        dto.setNick(entrenador.getNick());
        dto.setEsAdmin(entrenador.getEsAdmin());
        dto.setFechaNacimiento(entrenador.getFechaNacimiento());
        dto.setFoto(entrenador.getFoto());
        dto.setFechaAlta(entrenador.getFechaAlta());
        dto.setFechaUltimoLogin(entrenador.getFechaUltimoLogin());
        // Mapear los demás campos según sea necesario
       
        // Si tienes los ids del establecimiento y creador:
        dto.setEstablecimientoId(entrenador.getEstablecimiento().getId());
        dto.setCreadorId(entrenador.getCreador().getId());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


  

  




   
}

