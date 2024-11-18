package Clases.Usuarios;

import Clases.Gestion.Cuenta;
import Clases.Gestion.Mesa;
import Clases.Gestion.Pedido;
import ClasesGestoras.Mesas;
import Enums.EstadoMesa;
import Enums.EstadoPedido;
import Enums.MetodoPago;
import Excepciones.PedidoExcepcion;

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

    public void eliminarPedido(Pedido pedido) throws PedidoExcepcion {

    }
}
