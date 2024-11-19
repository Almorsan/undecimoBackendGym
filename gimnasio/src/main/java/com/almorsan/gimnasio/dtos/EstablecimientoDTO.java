
package com.almorsan.gimnasio.dtos;

import java.time.LocalDateTime;







public class EstablecimientoDTO {
    private String direccion;
    private String foto;
    private LocalDateTime fechaCreacionRegistro;
    private Long creadorId;  // ID del creador del establecimiento
    private Long entrenadorSupervisorId;  // ID del entrenador supervisor
    private Long establecimientoId;

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }

    public void setFechaCreacionRegistro(LocalDateTime fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Long creadorId) {
        this.creadorId = creadorId;
    }

    public Long getEntrenadorSupervisorId() {
        return entrenadorSupervisorId;
    }

    public void setEntrenadorSupervisorId(Long entrenadorSupervisorId) {
        this.entrenadorSupervisorId = entrenadorSupervisorId;
    }

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }
    
    
}
