package Clases.Usuarios;
import Clases.Gestion.Pedido;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;
import Excepciones.PedidoException;
import Interfaces.IGestorPedidos;

public class Cocinero extends Usuario implements IGestorPedidos{

    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }
    public int actualizarEstadoPedido(Pedidos pedidos, int nroPedido, EstadoPedido estado) {
        try {
            for (Pedido p : pedidos.getPedidos()) {
                if (p.getNumeroPedido() == nroPedido) {
                    return p.actualizarEstadoPedido(estado);
                }
            }
            throw new PedidoException("⚠️ No se encontró ningún pedido con ese número.");
        } catch (PedidoException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public String toString() {
        return "COCINERO: " +
                "ID: " + id +
                " NOMBRE: " + nombre + '\'' +
                " APELLIDO: '" + apellido + '\'' +
                " EMAIL: " + email + '\'';
    }

    @Override
    public String obtenerPedidos(Pedidos pedidos) {
        return pedidos.mostrarPedidos();
    }

}
