package Clases.Gestion;

import Enums.TipoDeBebida;

public class Bebida {
    public int numero;
    private String nombre;
    private double precio;
    private TipoDeBebida tipo;
    private static int contador=1;

    public Bebida(String nombre, double precio, TipoDeBebida tipo) {
        this.numero=contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Bebida() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoDeBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeBebida tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo +
                '}';
    }
}
