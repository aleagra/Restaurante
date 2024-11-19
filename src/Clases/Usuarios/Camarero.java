package Clases.Usuarios;

import Clases.Gestion.*;
import ClasesGestoras.Mesas;
import Enums.EstadoMesa;
import Enums.EstadoPedido;
import Enums.MetodoPago;
import Excepciones.PedidoExcepcion;

import java.util.List;
import java.util.Scanner;

public class Camarero {

    public void registrarPedido(Pedido pedido) throws PedidoExcepcion {
        if(pedido!=null){pedido.actualizarEstadoPedido(EstadoPedido.PENDIENTE);
        } else{throw new PedidoExcepcion("El pedido no pudo ser actualizado");}
        }

    public String verMesasDisponibles(Mesas m){
       String dispo = " ";
       dispo = m.mostrarMesasDisponibles();
       return dispo;

    }

    public Cuenta generarFactura(Cuenta cuenta,Cliente cliente,Pedido pedido)  {
        Scanner sc = new Scanner(System.in);
        char opc = 's';
        double dsc;
        int metodo = 0;
        cuenta.setCliente(cliente);

        System.out.println("Va a aplicar algun tipo de descuento sobre el total de la cuenta? (s/n)");
        opc = sc.next().charAt(0);
        if(opc=='s'||opc=='S'){
                System.out.println("Ingrese el porcentaje que desea descontar: ");
                dsc = sc.nextDouble();
                cuenta.setDescuento(dsc);
        }
        cuenta.setPedido(pedido);

        System.out.println("Que metodo va a utilizar para pagar? :");
        System.out.println("1) Efectivo");
        System.out.println("2) Debito");
        System.out.println("2) Credito");
        metodo = sc.nextInt();

        switch (metodo){
            case 1:{cuenta.setMetodoPago(MetodoPago.EFECTIVO);break;}
            case 2:{cuenta.setMetodoPago(MetodoPago.DEBITO);break;}
            case 3: {cuenta.setMetodoPago(MetodoPago.CREDITO); break;}
        }

        cuenta.calcularTotal();

        return cuenta;
    }

    public void eliminarPedidoCompletado(Pedido pedido, List<Pedido> pedidos, Cuenta cuenta, Cliente cliente) throws PedidoExcepcion {
        if (pedido == null) {
            throw new PedidoExcepcion("El pedido no puede ser null.");
        }

        if (pedidos == null || pedidos.isEmpty()) {
            throw new PedidoExcepcion("No hay pedidos registrados para eliminar.");
        }

        if (!pedidos.contains(pedido)) {
            throw new PedidoExcepcion("El pedido no se encuentra en la lista.");
        }

        Cuenta facturaGenerada = generarFactura(cuenta, cliente, pedido);
        if (facturaGenerada != null) {
            pedidos.remove(pedido);
            System.out.println("El pedido número " + pedido.getNumeroPedido() + " ha sido eliminado de la lista.");
        } else {
            throw new PedidoExcepcion("No se pudo generar la factura. El pedido no será eliminado.");
        }
    }

    public Pedido generarPedido(int opcion, Bebida beb, Plato pl,Cliente cl) throws PedidoExcepcion {
        Pedido pedido = new Pedido(null,cl);
        do {
            switch (opcion) {
                case 1: {
                    if (pedido.addPlato(pl)) {
                    }
                    throw new PedidoExcepcion("No se pudo agregar el plato");
                }
                case 2: {
                    if (pedido.addBebida(beb)) {
                    }
                    throw new PedidoExcepcion("No se pudo agregar la bebida");
                }
            }
        }while (opcion != 0);

        return pedido;
    }
}
