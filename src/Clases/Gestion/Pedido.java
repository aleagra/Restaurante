package Clases.Gestion;

import Enums.EstadoPedido;
import Excepciones.BebidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private int numeroPedido;
    private List<Plato> platos;
    private List<Bebida> bebidas;
    private EstadoPedido estado;

    public Pedido(int numeroPedido, EstadoPedido estado) {
        this.numeroPedido = numeroPedido;
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
        this.estado = estado;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void actualizarEstadoPedido(){
    }

    public int actualizarEstadoPedido(EstadoPedido nuevoEstado) {
        int flag = 0;
        if (nuevoEstado != null) {
            this.estado = nuevoEstado;
            flag = 1;
        }
        return flag;
    }
}
