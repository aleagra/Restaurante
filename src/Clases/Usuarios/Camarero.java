package Clases.Usuarios;

import Clases.Gestion.*;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import Enums.EstadoMesa;
import Enums.EstadoPedido;
import Enums.MetodoPago;
import Excepciones.ContraseniaException;
import Excepciones.PedidoExcepcion;

import java.util.List;
import java.util.Scanner;

public class Camarero extends Usuario {

    public Camarero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    public void registrarPedido(Pedido pedido) throws PedidoExcepcion {
        if(pedido!=null){pedido.actualizarEstadoPedido(EstadoPedido.PENDIENTE);
        } else{throw new PedidoExcepcion("El pedido no pudo ser actualizado");}
        }

    public String verMesasDisponibles(Mesas m){
       String dispo = " ";
       dispo = m.mostrarMesasDisponibles();
       return dispo;

    }

    private Pedido buscarPedido(int idCliente, int numPedido, Pedidos pedidos) {
        for (Pedido pedido : pedidos.getPedidos()) {
            if (pedido.getCliente().getId() == idCliente && pedido.getNumeroPedido() == numPedido) {
                return pedido;
            }
        }
        throw new RuntimeException("El id del cliente o el número de pedido es incorrecto");
    }

    public Cuenta generarFactura(int idCliente, int numPedido, Pedidos pedidos, MetodoPago metodoPago) {
        Pedido pedidoEncontrado = buscarPedido(idCliente, numPedido, pedidos);
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(pedidoEncontrado.getCliente());
        cuenta.setPedido(pedidoEncontrado);
        cuenta.setTotal(cuenta.calcularTotal());
        cuenta.setMetodoPago(metodoPago);
        return cuenta;
    }

    public void eliminarPedidoCompletado(int idCliente, int numPedido, Pedidos pedidos) throws PedidoExcepcion {
        Pedido pedidoEncontrado = buscarPedido(idCliente, numPedido, pedidos);
        pedidos.eliminarPedido(pedidoEncontrado);
    }

    public Pedido generarPedido(int opcion,int numero, Carta carta,Cliente cl) throws PedidoExcepcion {
        Pedido pedido = new Pedido(EstadoPedido.EN_PREPARACION,cl);

        do {
            switch (opcion) {
                case 1: {
                    Plato plato = carta.buscarPlatoPorId(numero);
                    if (pedido.addPlato(plato)) {
                    }
                    throw new PedidoExcepcion("No se pudo agregar el plato");
                }
                case 2: {
                    Bebida bebida = carta.buscarBebidaPorId(numero);
                    if (pedido.addBebida(bebida)) {
                    }
                    throw new PedidoExcepcion("No se pudo agregar la bebida");
                }
            }
        }while (opcion != 0);

        return pedido;
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
