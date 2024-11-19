
package com.almorsan.gimnasio.dtos;



import java.time.LocalDateTime;



public class SalaDTO {
    private Long id;  // No es necesario para POST, pero puede ser útil para PUT
    private String nombre;
    private String foto;
    private LocalDateTime fechaCreacionRegistro;
    private Long establecimientoId;
    private Long entrenadorSupervisorId;
    private Long creadorId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getEntrenadorSupervisorId() {
        return entrenadorSupervisorId;
    }

    public void setEntrenadorSupervisorId(Long entrenadorSupervisorId) {
        this.entrenadorSupervisorId = entrenadorSupervisorId;
    }

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Long creadorId) {
        this.creadorId = creadorId;
    }
}

