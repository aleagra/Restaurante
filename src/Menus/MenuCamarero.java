package Menus;

import Clases.Gestion.Cuenta;
import Clases.Gestion.Mesa;
import Clases.Gestion.Pedido;
import Clases.Usuarios.Camarero;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Usuarios;
import Enums.EstadoMesa;
import Enums.MetodoPago;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class MenuCamarero {
    private final Scanner scanner;

    public MenuCamarero() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Camarero camarero, Mesas gestionMesas, Pedidos pedidos, Usuarios usuarios, Carta carta) {
        int opcion;

        do {
            System.out.println("\n========== MENU CAMARERO ==========");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Ver mesas disponibles");
            System.out.println("3. Generar factura");
            System.out.println("4.Cambiar contraseña");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

                System.out.print("\nConsulte el ID del cliente: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Registrando pedido ---");
                        Cliente usuario = usuarios.buscarPorId(id);
                        Pedido pedido = camarero.generarPedido(carta, usuario);
                        System.out.println(pedidos.agregarPedido(pedido));
                        break;

                    case 2:
                        try {
                            JSONArray arregloMesas = new JSONArray(JSONUtiles.leerUnJson("mesas.json"));

                            if (arregloMesas.length() > 0) {
                                for (int i = 0; i < arregloMesas.length(); i++) {
                                    JSONObject jsonObject = arregloMesas.getJSONObject(i);

                                    Mesa mesita = new Mesa();
                                    mesita.fromJson(jsonObject);
                                    if (mesita.getEstadoMesa().equals(EstadoMesa.LIBRE)) {
                                        System.out.println("NUMERO:" + mesita.getNumero() + " " + "CAPACIDAD:" + mesita.getCapacidad() + " " + "ESTADO:" + mesita.getEstadoMesa());
                                    }
                                }
                            } else {
                                System.out.println("No se encontraron mesas en el archivo.");
                            }

                        } catch (JSONException e) {
                            System.out.println("No se ha podido leer el archivo.");
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Generando factura ---");
                        System.out.print("Ingrese el número de orden: ");
                        int numOrden = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea

                        MetodoPago metodoPago = camarero.consultarFormaDePago();
                        Cuenta factura = camarero.generarFactura(id, numOrden, pedidos, metodoPago);
                        camarero.eliminarPedidoCompletado(id, numOrden, pedidos);
                        System.out.println(factura);
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

        } while (opcion != 0);
    }
}















