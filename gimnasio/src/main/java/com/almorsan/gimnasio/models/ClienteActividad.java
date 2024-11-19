package com.almorsan.gimnasio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes_actividades")
public class ClienteActividad {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
