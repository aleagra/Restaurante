package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.MetodoPago;

public class Cuenta {
    private static int contador = 1;
    public int idCuenta;
    public Cliente cliente;
    public double total;
    public double descuento;
    public MetodoPago metodoPago;
    public Pedido pedido;

    public Cuenta(Cliente cliente, double total, double descuento,Pedido pedido) {
        this.idCuenta = contador++;
        this.cliente = cliente;
        this.total = total;
        this.descuento = descuento;
        this.metodoPago = MetodoPago.EFECTIVO;
        this.pedido = pedido;
    }

    public Cuenta() {
        this.idCuenta = contador++;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", cliente=" + cliente +
                ", total=" + total +
                ", descuento=" + descuento +
                ", metodoPago=" + metodoPago +
                ", pedido=" + pedido +
                '}';
    }

    public void aplicarDescuento() {
        this.total = this.total - (this.total * (this.descuento / 100));
    }

    public void calcularTotal() {
        double totalSinDescuento = 0;

        if (pedido != null) {
            for (Plato plato : pedido.getPlatos()) {
                totalSinDescuento += plato.getPrecio();
            }
            for (Bebida bebida : pedido.getBebidas()) {
                totalSinDescuento += bebida.getPrecio();
            }
        }
        this.total = totalSinDescuento;
        if (descuento > 0) {
            aplicarDescuento();
        }
    }
}
