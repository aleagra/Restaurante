package Clases.Gestion;

import Enums.TipoDePlato;

public class Plato {
    public int numero;
    private String nombre;
    private String descripcion;
    private double precio;
    private TipoDePlato categoria;

    private static int contador=1;

    public Plato(String nombre, String descripcion, double precio, TipoDePlato categoria) {
        this.numero = contador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Plato() {
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoDePlato getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoDePlato categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return
                "Nombre='" + nombre + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", Precio=" + precio +
                ", Categoria=" + categoria ;
    }
}
