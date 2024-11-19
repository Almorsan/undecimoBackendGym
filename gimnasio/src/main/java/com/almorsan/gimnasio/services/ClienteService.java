

package com.almorsan.gimnasio.services;

import com.almorsan.gimnasio.dtos.ClienteDTO;
import com.almorsan.gimnasio.models.Cliente;
import com.almorsan.gimnasio.models.Cliente.Sexo;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.repositories.ClienteRepository;
import com.almorsan.gimnasio.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

   public Cliente save(ClienteDTO clienteDTO) {
    Cliente cliente = new Cliente();
    cliente.setNombre(clienteDTO.getNombre());
    cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());

    // Convertir el valor del sexo de la cadena a Enum
    try {
        cliente.setSexo(Sexo.valueOf(clienteDTO.getSexo().toString()));
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Sexo inválido. Valores permitidos: M, F, Otro.");
    }

    cliente.setPeso(clienteDTO.getPeso());
    cliente.setAltura(clienteDTO.getAltura());
    cliente.setFoto(clienteDTO.getFoto());
    cliente.setFechaCreacionRegistro(clienteDTO.getFechaCreacionRegistro());

    // Buscar el entrenador (registro creado por) por ID
    Entrenador registroCreadoPor = entrenadorRepository.findById(clienteDTO.getRegistroCreadoPorId())
        .orElseThrow(() -> new RuntimeException("Entrenador creador no encontrado con ID: " + clienteDTO.getRegistroCreadoPorId()));
    cliente.setRegistroCreadoPor(registroCreadoPor);

    return clienteRepository.save(cliente);
}
   
   public Cliente actualizarCliente(Long clienteId, ClienteDTO clienteDTO) {
        // Verificar si el cliente existe
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        // Actualizar los campos solo si están presentes en el DTO
        if (clienteDTO.getNombre() != null) {
            cliente.setNombre(clienteDTO.getNombre());
        }
        if (clienteDTO.getFechaNacimiento() != null) {
            cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        }

        if (clienteDTO.getSexo() != null) {
            try {
                cliente.setSexo(Cliente.Sexo.valueOf(clienteDTO.getSexo().toString()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Sexo inválido. Valores permitidos: M, F, Otro.");
            }
        }

        if (clienteDTO.getPeso() != null) {
            cliente.setPeso(clienteDTO.getPeso());
        }
        if (clienteDTO.getAltura() != null) {
            cliente.setAltura(clienteDTO.getAltura());
        }
        if (clienteDTO.getFoto() != null) {
            cliente.setFoto(clienteDTO.getFoto());
        }
        if (clienteDTO.getFechaCreacionRegistro() != null) {
            cliente.setFechaCreacionRegistro(clienteDTO.getFechaCreacionRegistro());
        }

        // Actualizar el entrenador creador si está presente
        if (clienteDTO.getRegistroCreadoPorId() != null) {
            Entrenador registroCreadoPor = entrenadorRepository.findById(clienteDTO.getRegistroCreadoPorId())
                .orElseThrow(() -> new RuntimeException("Entrenador creador no encontrado con ID: " + clienteDTO.getRegistroCreadoPorId()));
            cliente.setRegistroCreadoPor(registroCreadoPor);
        }

        // Guardar los cambios
        return clienteRepository.save(cliente);
    }

}

