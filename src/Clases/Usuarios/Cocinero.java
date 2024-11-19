package Clases.Usuarios;

import Clases.Gestion.Pedido;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;
import Excepciones.ContraseniaException;
import Excepciones.PedidoException;

public class Cocinero extends Usuario {

    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    public String verPedido(Pedidos pedidos) {
        return pedidos.mostrarPedidos();
    }

    public int actualizarEstadoPedido(Pedidos pedidos, int nroPedido, EstadoPedido estado){
        for(Pedido p: pedidos.getPedidos()){
            if(p.getNumeroPedido() == nroPedido){
                return p.actualizarEstadoPedido(estado);
            }
        }
        throw new PedidoException("No se encontro ningun pedido con eso nro!");
    }

    @Override
    public String cambiarContrasenia(String contraActual, String contraNueva) {
        String msj = "Contrasenia cambiada.";
        if(contraActual.equals(this.contrasenia)){
            setContrasenia(contraNueva);
            return msj;
        }
        throw new ContraseniaException("La contrasenia no se pudo cambiar.");
    }
}
