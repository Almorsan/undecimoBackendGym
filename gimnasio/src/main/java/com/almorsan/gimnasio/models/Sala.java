package com.almorsan.gimnasio.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_sala", nullable = false)
    private String nombre;

    @Column(name = "foto")
    private String foto;

    @Column(name = "fecha_creacion_registro", nullable = false)
    private LocalDateTime fechaCreacionRegistro;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", nullable = false)
    @JsonIgnoreProperties({"entrenadores", "salas", "clientes", "entrenadorSupervisor"})
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "entrenador_supervisor_id")
    @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña,salasSupervisadas"})
    private Entrenador entrenadorSupervisor;
    
    @ManyToOne
    @JoinColumn(name = "registro_creado_por")
    @JsonIgnoreProperties({"establecimiento", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña","salasSupervisadas"})
    private Entrenador creador;
    
    @ManyToMany(mappedBy = "salas")
     @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña,salasSupervisadas"})
    private Set<Entrenador> entrenadores;
    
     @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"sala","entrenador", "establecimiento", "registroCreadoPor", "clientes"})  // Evitar la referencia recursiva
    private Set<Actividad> actividades;

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    public Set<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(Set<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
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

    public Entrenador getEntrenadorSupervisor() {
        return entrenadorSupervisor;
    }

    public void setEntrenadorSupervisor(Entrenador entrenadorSupervisor) {
        this.entrenadorSupervisor = entrenadorSupervisor;
    }

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

}
