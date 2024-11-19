package com.almorsan.gimnasio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entrenadores")

public class Entrenador {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nick", nullable = false, unique = true)
    private String nick;

    @JsonIgnore
    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "es_admin", nullable = false)
    private Boolean esAdmin;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "foto")
    private String foto;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_ultimo_login")
    private LocalDateTime fechaUltimoLogin;
    

    @ManyToOne
    @JoinColumn(name = "establecimiento_id")
    @JsonIgnoreProperties({"entrenadores", "salas", "clientes", "entrenadorSupervisor"})
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "registro_creado_por", nullable = false)
    @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña","salasSupervisadas"})
    private Entrenador creador;

    @OneToMany(mappedBy = "creador")
    @JsonIgnoreProperties({"establecimiento", "creador", "entrenadoresCreados", "clientes", "salas", "actividades","contraseña"})
    private Set<Entrenador> entrenadoresCreados = new HashSet<>();

    @ManyToMany (mappedBy = "entrenadores")
   @JsonIgnoreProperties({"registroCreadoPor", "establecimientos", "actividades", "entrenadores"})
   /*  @JoinTable(
            name = "clientes_entrenadores",
            joinColumns = @JoinColumn(name = "id_entrenador"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente")
    ) */
    private Set<Cliente> clientes;

    @ManyToMany
    @JsonIgnoreProperties({"establecimiento", "entrenadorSupervisor","entrenadores"})
    @JoinTable(
            name = "salas_entrenadores",
            joinColumns = @JoinColumn(name = "id_entrenador"),
            inverseJoinColumns = @JoinColumn(name = "id_sala")
    )
    private Set<Sala> salas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entrenador_id")
    @JsonIgnoreProperties({"entrenador", "establecimiento", "registroCreadoPor", "clientes"})
    private Set<Actividad> actividades = new HashSet<>();
    
    
@OneToMany(mappedBy = "entrenadorSupervisor")
@JsonIgnoreProperties({"entrenadorSupervisor", "clientes", "actividades", "establecimiento, creador","entrenadores"})
private Set<Sala> salasSupervisadas = new HashSet<>();

    public Set<Entrenador> getEntrenadoresCreados() {
        return entrenadoresCreados;
    }

    public Set<Sala> getSalasSupervisadas() {
        return salasSupervisadas;
    }

    public void setSalasSupervisadas(Set<Sala> salasSupervisadas) {
        this.salasSupervisadas = salasSupervisadas;
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

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
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

    public LocalDateTime getFechaUltimoLogin() {
        return fechaUltimoLogin;
    }

    public void setFechaUltimoLogin(LocalDateTime fechaUltimoLogin) {
        this.fechaUltimoLogin = fechaUltimoLogin;
    }

   

}
