package Menus;
import Clases.Gestion.Mesa;
import Clases.Gestion.Reserva;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenu(Scanner scanner, Mesas mesas, Cliente cliente, Reservas reservas, Pedidos pedidos) {
        int opcion;

        do {
            System.out.println("\n========== MENU CLIENTE ==========");
            System.out.println("1. Ver carta");
            System.out.println("2. Hacer una reserva");
            System.out.println("3. Ver reservas");
            System.out.println("4. Ver pedidos");
            System.out.println("5.Cambiar contraseña");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(cliente.mostrarCarta("carta.json"));
                    break;
                case 2:
                    System.out.println("\n--- Hacer una reserva ---");
                    System.out.print("¿Cuántas personas son? ");
                    int personas = scanner.nextInt();
                    Mesa mesa = mesas.asignarMesa(personas);
                    if (mesa != null) {
                        Reserva reserva = new Reserva(mesa, cliente);
                        cliente.crearReserva(reserva,reservas);
                        System.out.println("✅ La reserva fue creada con exito.");
                    } else {
                        System.out.println("❌ No hay mesas disponibles para esa cantidad de personas.");
                    }
                    break;

                case 3:
                    System.out.println(cliente.obtenerReservas());
                    break;
                case 4:
                    System.out.println("\n--- TUS PEDIDOS ---");
                    System.out.println(cliente.obtenerPedidos(pedidos));
                    break;

                case 5:
                    System.out.println("\n--- CAMBIO DE CONTRASEÑA ---");
                    System.out.print("Ingrese la contraseña actual: ");
                    String contraActual = scanner.nextLine();
                    System.out.print("Ingrese la nueva contraseña: ");
                    String contraNueva = scanner.nextLine();
                    System.out.println(cliente.cambiarContrasenia(contraActual, contraNueva));
                    break;
                default:
                    System.out.println("\n👋 Sesión cerrada. ¡Gracias por visitarnos!");
            }
        } while (opcion != 0);
    }




}
