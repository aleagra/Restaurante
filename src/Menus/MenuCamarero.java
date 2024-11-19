package Menus;

import Clases.Gestion.Cuenta;
import Clases.Gestion.Pedido;
import Clases.Usuarios.Camarero;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Mesas;

import java.util.List;
import java.util.Scanner;

public class MenuCamarero {
    private Scanner scanner;

    public MenuCamarero() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Camarero camarero, Mesas gestionMesas, List<Pedido> pedidos, Pedido pedido, Cliente cliente) {
        int opcion;

        do {
            System.out.println("------------------MENU CAMARERO------------------");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Ver mesas disponibles");
            System.out.println("3. Generar factura");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    camarero.registrarPedido(pedido);
                    System.out.println("Pedido registrado correctamente con estado: " + pedido.getEstado());
                    break;
                case 2:
                    String mesasDisponibles = camarero.verMesasDisponibles(gestionMesas);
                    if (mesasDisponibles.isEmpty()) {
                        System.out.println("No hay mesas disponibles.");
                    } else {
                        System.out.println("Mesas disponibles:\n" + mesasDisponibles);
                    }
                    break;
                case 3:
                    Cliente cliente = new Cliente();
                    Pedido pedido = new Pedido();
                    Cuenta cuenta = new Cuenta();
                    Cuenta factura = camarero.generarFactura(cuenta, cliente, pedido);
                    camarero.eliminarPedidoCompletado(pedido, pedidos, cuenta, cliente);
                    System.out.println("Factura generada correctamente. Total: $" + factura.getTotal());
                    break;
                default:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 4);
    }
}















