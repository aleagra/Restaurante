package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.MetodoPago;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Cuenta {
    private static int contador = 1;
    public int idCuenta;
    public Cliente cliente;
    public double total;
    public MetodoPago metodoPago;
    public Pedido pedido;

    public Cuenta() {
        this.idCuenta = contador++;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    @Override
    public String toString() {
        return "NUMERO DE PEDIDO: " + idCuenta +
                "\nNOMBRE: "+  cliente.getNombre()+
                "\nTOTAL: " + total +
                "\nMETODO DE PAGO: " + metodoPago +
                "\nPEDIDO: " + pedido.getEstado() +
                "\n\nBEBIDAS: "+ pedido.getNombresBebidas()+
                "\nPLATOS: "+ pedido.getNombresPlatos();
    }
}