package com.almorsan.gimnasio.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "establecimientos")

public class Establecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "foto")
    private String foto;

    @Column(name = "fecha_creacion_registro", nullable = false)
    private LocalDateTime fechaCreacionRegistro;

    @OneToMany(mappedBy = "establecimiento")
    @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña","salasSupervisadas"})
    private Set<Entrenador> entrenadores;

    @OneToMany(mappedBy = "establecimiento")
    @JsonIgnoreProperties({"establecimiento", "entrenadorSupervisor","entrenadores"})
    private Set<Sala> salas;

   @ManyToMany(mappedBy = "establecimientos")
    @JsonIgnoreProperties({"registroCreadoPor", "establecimientos", "actividades", "entrenadores"})
    /*@JoinTable(
            name = "clientes_establecimientos",
            joinColumns = @JoinColumn(name = "id_establecimiento"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente")
    )*/
    private Set<Cliente> clientes;

    @ManyToOne
    @JoinColumn(name = "entrenador_supervisor_id")
    @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña","salasSupervisadas"})
    private Entrenador entrenadorSupervisor;
    
    @ManyToOne
    @JoinColumn(name = "registro_creado_por")
    @JsonIgnoreProperties({"establecimiento", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña","salasSupervisadas"})
    private Entrenador creador;

    public Set<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Entrenador getEntrenadorSupervisor() {
        return entrenadorSupervisor;
    }

    public void setEntrenadorSupervisor(Entrenador entrenadorSupervisor) {
        this.entrenadorSupervisor = entrenadorSupervisor;
    }

    public Set<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(Set<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

   /* public void setRegistroCreadoPor(Entrenador registroCreadoPor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } */
    
    public Entrenador getCreador() {
    return creador;
}

public void setCreador(Entrenador creador) {
    this.creador = creador;
}

}
