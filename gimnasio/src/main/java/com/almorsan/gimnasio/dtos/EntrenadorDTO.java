
package com.almorsan.gimnasio.dtos;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Cliente;
import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.models.Sala;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class EntrenadorDTO {

    private String nombre;
    private String nick;
    private String contraseña;
    private Boolean esAdmin;
    private LocalDate fechaNacimiento;
    private String foto;
    private LocalDateTime fechaAlta;
    private LocalDateTime FechaUltimoLogin;
    private Set<Entrenador> entrenadoresCreados = new HashSet<>();
    private Entrenador creador;
    private Establecimiento establecimiento;
    private Set<Cliente> clientes;
    private Set<Sala> salas;
    private Set<Actividad> actividades = new HashSet<>();
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
    

    public LocalDateTime getFechaUltimoLogin() {
        return FechaUltimoLogin;
    }

    public Set<Entrenador> getEntrenadoresCreados() {
        return entrenadoresCreados;
    }

    public void setEntrenadoresCreados(Set<Entrenador> entrenadoresCreados) {
        this.entrenadoresCreados = entrenadoresCreados;
    }

    public Entrenador getCreador() {
        return creador;
    }

    public void setCreador(Entrenador creador) {
        this.creador = creador;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void setFechaUltimoLogin(LocalDateTime FechaUltimoLogin) {
        this.FechaUltimoLogin = FechaUltimoLogin;
    }
    private Long establecimientoId;
    private Long creadorId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Long creadorId) {
        this.creadorId = creadorId;
    }

    
   
    
    
}

