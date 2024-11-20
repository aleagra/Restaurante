package Menus;

import Clases.Gestion.Cuenta;
import Clases.Gestion.Pedido;
import Clases.Usuarios.Camarero;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Usuarios;
import Enums.MetodoPago;
import java.util.Scanner;

public class MenuCamarero {
    private final Scanner scanner;

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
                    Pedido pedido;
                    Cliente usuario= usuarios.buscarPorId(id);
                    pedido = camarero.generarPedido(carta,usuario);
                    System.out.println(pedidos.agregarPedido(pedido));
                    break;
                case 2:
                    System.out.println(camarero.verMesasDisponibles(gestionMesas));
                    break;
                case 3:
                    System.out.println("Ingrese el numero de orden:");
                    int numOrden = scanner.nextInt();
                    MetodoPago metodoPago = camarero.consultarFormaDePago();
                    Cuenta factura = camarero.generarFactura(id,numOrden,pedidos,metodoPago);
                    camarero.eliminarPedidoCompletado(id,numOrden,pedidos);
                    System.out.println(factura);
                    break;
                default:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 4);
    }
}















