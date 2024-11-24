package Menus;
import Clases.Gestion.Cuenta;
import Clases.Gestion.Pedido;
import Clases.Usuarios.Camarero;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Usuarios;
import Enums.MetodoPago;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCamarero {

    public void mostrarMenu(Scanner scanner,Camarero camarero, Pedidos pedidos, Usuarios usuarios) {
        int opcion = 0;

        do {
            try{
                System.out.println("\n========== MENU CAMARERO ==========");
                System.out.println("1. Registrar pedido");
                System.out.println("2. Ver mesas disponibles");
                System.out.println("3. Generar factura");
                System.out.println("4.Cambiar contraseña");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("\nConsulte el ID del cliente: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\n--- Registrando pedido ---");
                        Cliente usuario = usuarios.buscarPorId(id);
                        Pedido pedido = camarero.generarPedido(usuario);
                        System.out.println(pedidos.agregarPedido(pedido));
                        break;

                    case 2:
                        System.out.println(camarero.mostrarMesas("mesas.json"));
                        break;
                    case 3:
                        System.out.print("\nConsulte el ID del cliente: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\n--- Generando factura ---");
                        System.out.print("Ingrese el número de orden: ");
                        int numOrden = scanner.nextInt();
                        scanner.nextLine();
                        MetodoPago metodoPago = camarero.consultarFormaDePago();
                        Cuenta factura = camarero.generarFactura(id, numOrden, pedidos, metodoPago);
                        if (factura == null) {
                            System.out.println("❌ No hay ningun pedido asociado a ese cliente.");
                        }else {
                            camarero.eliminarPedidoCompletado(id, numOrden, pedidos);
                            System.out.println(factura);
                        }
                        break;
                    case 4:
                        System.out.println("\n--- Cambio de contraseña ---");
                        System.out.print("Ingrese la contraseña actual: ");
                        String contraActual = scanner.nextLine();
                        System.out.print("Ingrese la nueva contraseña: ");
                        String contraNueva = scanner.nextLine();
                        System.out.println(camarero.cambiarContrasenia(contraActual, contraNueva));
                        break;

                    default:
                        System.out.println("\n👋 Sesión cerrada. ¡Gracias por visitarnos!");
                }
            }catch (InputMismatchException e){
                System.out.println("⚠️ Entrada inválida. Por favor, vuelva a iniciar sesión.");
                scanner.nextLine();
            }

        } while (opcion != 0);
    }



}















