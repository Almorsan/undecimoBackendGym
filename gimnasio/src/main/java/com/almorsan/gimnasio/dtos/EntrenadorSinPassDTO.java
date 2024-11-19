

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

public class EntrenadorSinPassDTO {

    private String nombre;
    private String nick;
    private Boolean esAdmin;
    private LocalDate fechaNacimiento;
    private String foto;
    private LocalDateTime fechaAlta;
    private LocalDateTime FechaUltimoLogin;
    

  
   
    public LocalDateTime getFechaUltimoLogin() {
        return FechaUltimoLogin;
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


