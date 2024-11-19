package Clases.Usuarios;

import Clases.Gestion.Pedido;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;

public class Cocinero extends Usuario{
    Pedidos pedidos;

    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
        this.pedidos = new Pedidos();
    }

    @Override
    public boolean cambiarContrasenia() {
        return false;
    }

    public String verPedido(){
        return pedidos.mostrarPedidos();
    }

    public int actualizarEstadoPedido(Pedido pedido){
       return pedido.actualizarEstadoPedido(EstadoPedido.LISTO);
    }
}
