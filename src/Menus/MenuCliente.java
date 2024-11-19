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

    public void MostrarMenu(Scanner scanner,Mesas mesas,Cliente cliente,Carta carta,Reservas reservas,Pedidos pedidos){
        int opcion;
        do {
            System.out.println("1- Hacer una reserva");
            System.out.println("2- Ver reservas");
            System.out.println("3- Ver pedidos");
            System.out.println("4- Ver estado de un pedido");
            System.out.println("4- Cerrar sesion");
            opcion= scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    Mesa mesa=new Mesa();
                    System.out.println("Creando reserva...");
                    System.out.println("Cuantas personas son? \n");
                    opcion = scanner.nextInt();
                    mesa=mesas.asignarMesa(opcion);
                    Reserva reserva = new Reserva(mesa,cliente);
                    reservas.aniadirReserva(reserva);
                    break;
                case 2:
                    System.out.println(reservas.mostrarReservasPorCliente(cliente.getEmail()));
                    break;
                case 3:
                    System.out.println(pedidos.mostrarPedidosPorCliente(cliente.getEmail()));
                    break;
                case 4:
                    break;
            }
        }while (opcion != 5);


    }

}
