package Clases;

import Enums.EstadoMesa;

public class Mesa {
    private static int contador=1;
    public int numero;
    public int capacidad;
    public EstadoMesa estadoMesa;

    public Mesa(int capacidad, EstadoMesa estadoMesa) {
        this.numero = contador++;
        this.capacidad = capacidad;
        this.estadoMesa = estadoMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public EstadoMesa getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "numero=" + numero +
                ", capacidad=" + capacidad +
                ", estadoMesa=" + estadoMesa +
                '}';
    }
}