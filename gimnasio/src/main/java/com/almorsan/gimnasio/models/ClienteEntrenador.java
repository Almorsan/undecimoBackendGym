package com.almorsan.gimnasio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes_entrenadores")
public class ClienteEntrenador {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

}
