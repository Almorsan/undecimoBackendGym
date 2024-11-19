package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/entrenadores")
public class EntrenadorController {


    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> encontrarEntrenadorPorId(@PathVariable Long id) {
        Optional<Entrenador> entrenador = entrenadorRepository.findById(id);

        return entrenador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @CrossOrigin
     @GetMapping("/api/{nick}")
    public ResponseEntity<Entrenador> encontrarEntrenadorPorNick(@PathVariable String nick) {
        Entrenador entrenador = entrenadorRepository.findByNick(nick);

        if (entrenador != null) {
            return ResponseEntity.ok(entrenador);  
        } else {
            return ResponseEntity.notFound().build();  
        }
    }

    
    
    
   
    
    

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador entrenadorGuardado = entrenadorRepository.save(entrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorGuardado);

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEntrenador(@PathVariable Long id) {
        if (!entrenadorRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        entrenadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @CrossOrigin
@PatchMapping("/{id}")
public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenadorDetalles) {
    Optional<Entrenador> entrenadorOptional = entrenadorRepository.findById(id);

    if (entrenadorOptional.isPresent()) {
        Entrenador entrenadorExistente = entrenadorOptional.get();

        
        if (entrenadorDetalles.getNombre() != null) {
            entrenadorExistente.setNombre(entrenadorDetalles.getNombre());
        }
        if (entrenadorDetalles.getNick() != null) {
            entrenadorExistente.setNick(entrenadorDetalles.getNick());
        }
       

        Entrenador entrenadorActualizado = entrenadorRepository.save(entrenadorExistente);
        return ResponseEntity.ok(entrenadorActualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@CrossOrigin
@PatchMapping("/nick/{nick}")
public ResponseEntity<Entrenador> actualizarEntrenadorPorNick(@PathVariable String nick, @RequestBody Entrenador entrenadorDetalles) {
    Entrenador entrenadorExistente = entrenadorRepository.findByNick(nick);

    if (entrenadorExistente != null) {
       
        if (entrenadorDetalles.getNombre() != null) {
            entrenadorExistente.setNombre(entrenadorDetalles.getNombre());
        }
        if (entrenadorDetalles.getNick() != null) {
            entrenadorExistente.setNick(entrenadorDetalles.getNick());
        }
        
         if (entrenadorDetalles.getEsAdmin() != null) {
                entrenadorExistente.setEsAdmin(entrenadorDetalles.getEsAdmin());
            }
            if (entrenadorDetalles.getFechaNacimiento() != null) {
                entrenadorExistente.setFechaNacimiento(entrenadorDetalles.getFechaNacimiento());
            }
            if (entrenadorDetalles.getFoto() != null) {
                entrenadorExistente.setFoto(entrenadorDetalles.getFoto());
            }
            if (entrenadorDetalles.getFechaAlta() != null) {
                entrenadorExistente.setFechaAlta(entrenadorDetalles.getFechaAlta());
            }
            if (entrenadorDetalles.getFechaUltimoLogin() != null) {
                entrenadorExistente.setFechaUltimoLogin(entrenadorDetalles.getFechaUltimoLogin());
            }
        

        Entrenador entrenadorActualizado = entrenadorRepository.save(entrenadorExistente);
        return ResponseEntity.ok(entrenadorActualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}


}
