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
       String msj = " ";
       msj = m.mostrarMesasDisponibles();
       if (m.mostrarMesasDisponibles().isEmpty()){
          msj="No hay mesas disponibles actualmente";
       }
       return msj;

    }

    public MetodoPago consultarFormaDePago() {
        Scanner scanner = new Scanner(System.in);
        MetodoPago metodoPago = null;

        while (metodoPago == null) { // Repetir hasta que se elija una opción válida
            System.out.println("Consulte la forma de pago: (1- Efectivo, 2- Débito, 3- Crédito)");
            int formaPago = scanner.nextInt();

            switch (formaPago) {
                case 1:
                    metodoPago = MetodoPago.EFECTIVO;
                    break;
                case 2:
                    metodoPago = MetodoPago.DEBITO;
                    break;
                case 3:
                    metodoPago = MetodoPago.CREDITO;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        return metodoPago;
    }

    private Pedido buscarPedido(int idCliente, int numPedido, Pedidos pedidos) {
        Pedido pedidoAux = new Pedido();
        for (Pedido pedido : pedidos.getPedidos()) {
            if (pedido.getCliente().getId() == idCliente && pedido.getNumeroPedido() == numPedido) {
                pedidoAux = pedido;
            }
        }
        if (pedidoAux == null){
            throw new RuntimeException("El numero de pedido o id de cliente no son correctos");
        }
        return pedidoAux;
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

    public Pedido generarPedido(Carta carta, Cliente cliente) throws PedidoExcepcion {
        Pedido pedido = new Pedido(EstadoPedido.EN_PREPARACION, cliente);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar un plato");
            System.out.println("2. Agregar una bebida");
            System.out.println("0. Finalizar pedido");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese el ID del plato:");
                    int numero = scanner.nextInt();
                    Plato plato = carta.buscarPlatoPorId(numero);
                    if (plato != null) {
                        pedido.addPlato(plato);
                        System.out.println("Plato agregado al pedido.");
                    } else {
                        System.out.println("Plato no encontrado.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Ingrese el ID de la bebida:");
                    int numero = scanner.nextInt();
                    Bebida bebida = carta.buscarBebidaPorId(numero);
                    if (bebida != null) {
                        pedido.addBebida(bebida);
                        System.out.println("Bebida agregada al pedido.");
                    } else {
                        System.out.println("Bebida no encontrada.");
                    }
                    break;
                }
                case 0: {
                    System.out.println("Pedido finalizado.");
                    break;
                }
                default: {
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
                }
            }
        } while (opcion != 0);

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
