package Clases.Usuarios;

import Clases.Gestion.Pedido;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;

public class Cocinero extends Usuario{
    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }






    @Override
    public boolean cambiarContrasenia(String contraseña) {
        setContrasenia(contraseña);
    }

    public String verPedido(Pedidos pedidos) {
        return pedidos.mostrarPedidos();
    }

    public void actualizarEstadoPedido(Pedidos pedidos, int nroPedido, EstadoPedido estado){
        for(Pedido p: pedidos){
            if(p.getNumeroPedido() == nroPedido){
                p.setEstado(estado);
            }
        }
    }





}
