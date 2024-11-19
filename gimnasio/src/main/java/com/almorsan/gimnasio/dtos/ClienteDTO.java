package com.almorsan.gimnasio.dtos;

import java.time.LocalDateTime;

public class ClienteDTO {

    private Long id;  // No es necesario para POST, pero útil para PUT
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private Sexo sexo;
    private Double peso;
    private Double altura;
    private String foto;
    private LocalDateTime fechaCreacionRegistro;
    private Long registroCreadoPorId;

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

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
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

    public Long getRegistroCreadoPorId() {
        return registroCreadoPorId;
    }

    public void setRegistroCreadoPorId(Long registroCreadoPorId) {
        this.registroCreadoPorId = registroCreadoPorId;
    }

    public enum Sexo {
        M, F, Otro
    }
}

