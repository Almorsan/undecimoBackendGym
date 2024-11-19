
package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.dtos.TipoActividadDTO;
import com.almorsan.gimnasio.models.TipoActividad;
import com.almorsan.gimnasio.services.TipoActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/tipoActividad")
public class TipoActividadDTOController {

    @Autowired
    private TipoActividadService tipoActividadService;

    @PostMapping
    public ResponseEntity<TipoActividad> crearTipoActividad(@RequestBody TipoActividadDTO tipoActividadDTO) {
        TipoActividad tipoActividad = tipoActividadService.save(tipoActividadDTO);
        return ResponseEntity.ok(tipoActividad);
    }
    
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoActividad updateTipoActividad(@PathVariable Long id, @RequestBody TipoActividadDTO tipoActividadDTO) {
        return tipoActividadService.patch(id, tipoActividadDTO);
    }
}