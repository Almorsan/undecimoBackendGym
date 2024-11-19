

package com.almorsan.gimnasio.controllers;

import com.almorsan.gimnasio.dtos.ClienteDTO;
import com.almorsan.gimnasio.models.Cliente;
import com.almorsan.gimnasio.services.ClienteActividadService;
import com.almorsan.gimnasio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteDTOController {

    @Autowired
    private ClienteService clienteService;
    
    
    @Autowired
    private ClienteActividadService clienteActividadService;

    @PostMapping
    public Cliente crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.save(clienteDTO);
    }
    
    @PatchMapping("/{clienteId}")
    public Cliente actualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.actualizarCliente(clienteId, clienteDTO);
    }
    
      @DeleteMapping("/clientes/{clienteId}/actividad/{actividadId}")
    public ResponseEntity<String> eliminarClienteDeActividad(
            @PathVariable Long clienteId,
            @PathVariable Long actividadId) {
        
        try {
            // Llamamos al servicio para eliminar la relación
            String response = clienteActividadService.eliminarClienteDeActividad(clienteId, actividadId);
            return ResponseEntity.ok(response);  // Devolvemos un mensaje exitoso
        } catch (RuntimeException e) {
            // En caso de error, devolvemos un mensaje con el error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
}

