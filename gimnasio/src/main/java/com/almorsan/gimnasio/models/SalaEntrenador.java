package com.almorsan.gimnasio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salas_entrenadores")
public class SalaEntrenador {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

}
