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

    public void mostrarMenu(Scanner scanner, Mesas mesas, Cliente cliente, Reservas reservas, Pedidos pedidos, Carta carta) {
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
                    System.out.println("\n--- Carta del Restaurante ---");
                    System.out.println("🍹 Bebidas:");
                    System.out.println(carta.mostrarBebidas());
                    System.out.println("🍽️ Platos:");
                    System.out.println(carta.mostrarComidas());
                    break;
                case 2:
                    System.out.println("\n--- Hacer una reserva ---");
                    System.out.print("¿Cuántas personas son? ");
                    int personas = scanner.nextInt();
                    Mesa mesa = mesas.asignarMesa(personas);
                    if (mesa != null) {
                        System.out.println("✔️ La reserva fue creada correctamente.");
                        Reserva reserva = new Reserva(mesa, cliente);
                        reservas.aniadirReserva(reserva);
                    } else {
                        System.out.println("❌ No hay mesas disponibles para esa cantidad de personas.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Tus Reservas ---");
                    System.out.println(cliente.obtenerReservas(reservas));
                    break;

                case 4:
                    System.out.println("\n--- Tus Pedidos ---");
                    System.out.println(cliente.obtenerPedidos(pedidos));
                    break;

                case 5:
                    System.out.println("\n--- Cambio de contraseña ---");
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
