package Menus;

import Clases.Gestion.Cuenta;
import Clases.Gestion.Pedido;
import Clases.Usuarios.Camarero;
import Clases.Usuarios.Cliente;
import Clases.Usuarios.Usuario;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Usuarios;
import Enums.MetodoPago;

import java.util.List;
import java.util.Scanner;

public class MenuCamarero {
    private Scanner scanner;

    public MenuCamarero() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Camarero camarero, Mesas gestionMesas, Pedidos pedidos, Usuarios usuarios, Carta carta) {
        int opcion;

        do {
            System.out.println("------------------MENU CAMARERO------------------");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Ver mesas disponibles");
            System.out.println("3. Generar factura");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Consulte el id del cliente:");
            int id= scanner.nextInt();

            switch (opcion) {
                case 1:
                    Pedido pedido = new Pedido();

                    Cliente usuario1= usuarios.buscarPorId(id);
                    System.out.println(carta.mostrarComidas());
                    System.out.println(carta.mostrarBebidas());
                    camarero.generarPedido(carta,usuario1);
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
                    System.out.println("Ingrese numero de orden:");
                    int numOrden = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Consulte la forma de pago: (1- Efectivo 2- Debito 3- Credito)");
                    int formaPago = scanner.nextInt();
                    MetodoPago metodoPago = null;
                    if(formaPago == 1) {
                    metodoPago = MetodoPago.EFECTIVO;
                    } else if (formaPago == 2) {
                        metodoPago = MetodoPago.DEBITO;
                    } else if (formaPago == 3) {
                        metodoPago = MetodoPago.CREDITO;
                    }
                    Cuenta factura = camarero.generarFactura(id,numOrden,pedidos,metodoPago);
                    camarero.eliminarPedidoCompletado(id,numOrden,pedidos);
                    double total = factura.calcularTotal();
                    System.out.println("Factura generada correctamente. Total: $" + total);
                    break;
                default:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 4);
    }
}















