
package com.almorsan.gimnasio.controllers;



import com.almorsan.gimnasio.dtos.LoginDTO;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import com.almorsan.gimnasio.services.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Entrenador entrenador = entrenadorRepository.findByNick(loginDTO.getNick());

        if (entrenador != null && passwordMatches(entrenador, loginDTO.getPassword())) {
            // Lógica de autenticación exitosa
            return ResponseEntity.ok("Login exitoso");
        } else {
            // Lógica de error en autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    private boolean passwordMatches(Entrenador entrenador, String password) {
        // Aquí va la lógica para comparar la contraseña
        return entrenador.getContraseña().equals(password);
    }
}
