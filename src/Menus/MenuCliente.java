package Menus;
import Clases.Gestion.Mesa;
import Clases.Gestion.Reserva;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import java.util.Scanner;

public class MenuCliente {

    public void MostrarMenu(Scanner scanner, Mesas mesas, Cliente cliente, Reservas reservas, Pedidos pedidos, Carta carta){
        int opcion;
        do {
            System.out.println("1- Ver carta");
            System.out.println("2- Hacer una reserva");
            System.out.println("3- Ver reservas");
            System.out.println("4- Ver pedidos");
            System.out.println("5- Cerrar sesion");
            opcion= scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("BEBIDAS");
                    System.out.println(carta.mostrarBebidas());
                    System.out.println("PLATOS");
                    System.out.println(carta.mostrarComidas());
                    break;
                case 2:
                    Mesa mesa;
                    System.out.println("Cuantas personas son?");
                    opcion = scanner.nextInt();
                    mesa=mesas.asignarMesa(opcion);
                    if (mesa != null){
                        System.out.println("La reserva fue creada correctamente.");
                    }
                    Reserva reserva = new Reserva(mesa,cliente);
                    reservas.aniadirReserva(reserva);
                    break;
                case 3:
                    System.out.println(reservas.mostrarReservasPorCliente(cliente.getEmail()));
                    break;
                case 4:
                    System.out.println(pedidos.mostrarPedidosPorCliente(cliente.getEmail()));
                    break;
                default:
                    System.out.println("SESION CERRADA...");
                    break;
            }
        }while (opcion != 5);


    }

}
