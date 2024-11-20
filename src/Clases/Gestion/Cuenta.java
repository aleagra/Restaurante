package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.MetodoPago;

public class Cuenta {
    private static int contador = 1;
    public int idCuenta;
    public Cliente cliente;
    public double total;
    public MetodoPago metodoPago;
    public Pedido pedido;

    public Cuenta(Cliente cliente, double total, double descuento, Pedido pedido) {
        this.idCuenta = contador++;
        this.cliente = cliente;
        this.total = total;
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
                "Numero de pedido=" + idCuenta +
                ",ID Cliente=" + cliente.getId() +
                ",Nombre="+  cliente.getNombre()+
                ",total=" + total +
                ",MetodoPago=" + metodoPago +
                ",pedido=" + pedido.getEstado() +
                ",Bebidas="+ pedido.getBebidas()+
                ",Platos="+ pedido.getNombresPlatos()+
                '}';
    }

    public double calcularTotal() {
        double total = 0;

        if (pedido != null) {
            if (pedido.getPlatos() != null) {
                for (Plato plato : pedido.getPlatos()) {
                    total += plato.getPrecio();
                }
            }

            if (pedido.getBebidas() != null) {
                for (Bebida bebida : pedido.getBebidas()) {
                    total += bebida.getPrecio();
                }
            }
        }

        return total;
    }
}