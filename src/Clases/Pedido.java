package Clases;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<Plato> platos;
    private List<Bebida> bebidas;
    private EstadoPedido estado;
    private double total;

    public Pedido(int numeroPedido, EstadoPedido estado, double total) {
        this.numeroPedido = numeroPedido;
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
        this.estado = estado;
        this.total = total;
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
