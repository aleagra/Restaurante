package Menus;

import Clases.Usuarios.Cocinero;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;

import java.util.Scanner;

public class MenuCocinero {

    public void mostrarMenuCocinero(Pedidos pedidos, Scanner sc, Cocinero cocinera) {
        int opcion;
        do {
            System.out.println("\n========== MENU COCINERO ==========");
            System.out.println("1. Ver pedidos");
            System.out.println("2. Actualizar estado del pedido");
            System.out.println("3. Cambiar contraseña");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Mostrando pedidos ---");
                    cocinera.obtenerPedidos(pedidos);
                    break;

                case 2:
                    System.out.println("\n--- Actualizando estado del pedido ---");
                    System.out.print("Ingrese el número del pedido: ");
                    int nroPedido = sc.nextInt();
                    cocinera.actualizarEstadoPedido(pedidos, nroPedido, EstadoPedido.LISTO);
                    break;

                case 3:
                    System.out.println("\n--- Cambiar contraseña ---");
                    System.out.print("Ingrese la contraseña actual: ");
                    String contraActual = sc.nextLine();
                    System.out.print("Ingrese la nueva contraseña: ");
                    String contraNueva = sc.nextLine();
                    System.out.println(cocinera.cambiarContrasenia(contraActual, contraNueva));
                    break;

                default:
                    System.out.println("\n👨‍🍳 Finalizando sesión del cocinero...");
                    break;
            }
        } while (opcion != 0);
    }
}
