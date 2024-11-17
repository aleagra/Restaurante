package Clases;

import Enums.MetodoPago;

public class Cuenta {
    private static int contador = 1;
    public int idCuenta;
    public Cliente cliente;
    public double total;
    public double descuento;
    public MetodoPago metodoPago;

    public Cuenta(int idCuenta, Cliente cliente, double total, double descuento) {
        this.idCuenta = contador++;
        this.cliente = cliente;
        this.total = total;
        this.descuento = descuento;
        this.metodoPago = MetodoPago.EFECTIVO;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Cuenta.contador = contador;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", cliente=" + cliente +
                ", total=" + total +
                ", descuento=" + descuento +
                ", metodoPago=" + metodoPago +
                '}';
    }

    public void aplicarDescuento(){

    };

    public void calcularTotal(){
        
    }
}
