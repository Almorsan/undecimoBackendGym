package com.almorsan.gimnasio.controllers;


import com.almorsan.gimnasio.dtos.EstablecimientoDTO;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.services.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoDTOController {



    @Autowired
    private EstablecimientoService establecimientoService;

    @PostMapping
    public Establecimiento crearEstablecimiento(@RequestBody EstablecimientoDTO establecimientoDTO) {
        return establecimientoService.save(establecimientoDTO); // Cambié el método a 'save'
    }
    
     @PutMapping("/{id}")
    public ResponseEntity<Establecimiento> updateEstablecimiento(
            @PathVariable("id") Long establecimientoId,
            @RequestBody EstablecimientoDTO establecimientoDTO) {

        try {
            Establecimiento updatedEstablecimiento = establecimientoService.update(establecimientoId, establecimientoDTO);
            return new ResponseEntity<>(updatedEstablecimiento, HttpStatus.OK);
        } catch (RuntimeException e) {
            // En caso de error, devolver el mensaje de excepción
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    
        // Método PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Establecimiento> patchEstablecimiento(
            @PathVariable("id") Long establecimientoId,
            @RequestBody EstablecimientoDTO establecimientoDTO) {

        try {
            Establecimiento updatedEstablecimiento = establecimientoService.update(establecimientoId, establecimientoDTO);
            return new ResponseEntity<>(updatedEstablecimiento, HttpStatus.OK);
        } catch (RuntimeException e) {
            // En caso de error, devolver el mensaje de excepción
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
}


    

