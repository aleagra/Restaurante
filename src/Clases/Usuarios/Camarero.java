package Clases.Usuarios;

import Clases.Gestion.Pedido;
import Enums.EstadoPedido;

public class Camarero {

    public void registrarPedido(Pedido pedido) {pedido.actualizarEstadoPedido(EstadoPedido.PENDIENTE);}

    public void verMesasDisponibles(){

    }

    public void generarFactura(){

    }
}
