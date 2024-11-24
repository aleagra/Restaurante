package Menus;
import Clases.Usuarios.Cocinero;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCocinero {

    public void mostrarMenuCocinero(Pedidos pedidos, Scanner sc, Cocinero cocinero) {
        int opcion = 0;

        do {
            try{
                System.out.println("\n========== MENU COCINERO ==========");
                System.out.println("1. Ver pedidos");
                System.out.println("2. Actualizar estado del pedido");
                System.out.println("3. Cambiar contraseña");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Mostrando pedidos ---");
                        System.out.println(cocinero.obtenerPedidos(pedidos));
                        break;

                    case 2:
                        System.out.println("\n--- Actualizando estado del pedido ---");
                        System.out.print("Ingrese el número del pedido: ");
                        int nroPedido = sc.nextInt();
                        int pedido = cocinero.actualizarEstadoPedido(pedidos, nroPedido, EstadoPedido.LISTO);
                        if(pedido != -1){
                            System.out.println("✅El pedido " + nroPedido + " fue actualizado correctamente.");
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Cambiar contraseña ---");
                        System.out.print("Ingrese la contraseña actual: ");
                        String contraActual = sc.nextLine();
                        System.out.print("Ingrese la nueva contraseña: ");
                        String contraNueva = sc.nextLine();
                        System.out.println(cocinero.cambiarContrasenia(contraActual, contraNueva));
                        break;

                    default:
                        System.out.println("\n👨‍🍳 Finalizando sesión del cocinero...");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número.");
                sc.nextLine();
            }
        } while (opcion != 0);
    }
}
