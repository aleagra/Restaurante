package Menus;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import Clases.Gestion.Reserva;
import Clases.Usuarios.Cliente;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import Enums.EstadoMesa;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenu(Scanner scanner, Mesas mesas, Cliente cliente, Reservas reservas, Pedidos pedidos, Carta carta, JSONArray jsonReservas) {
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
                    mostrarCartaDesdeJson("carta.json");
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
                        try {
                            jsonReservas.put(reserva.toJson());
                            JSONUtiles.grabarUnJson(reservas.toJson(), "reservas.json");
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("❌ No hay mesas disponibles para esa cantidad de personas.");
                    }
                    break;

                case 3:
                    mostrarReservasPorCliente("reservas.json", cliente.getEmail());
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

    public static void mostrarCartaDesdeJson(String rutaArchivoJson) {
        try {
            JSONArray arregloCarta = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloCarta.length() > 0) {
                for (int i = 0; i < arregloCarta.length(); i++) {
                    JSONObject jsonObject = arregloCarta.getJSONObject(i);

                    Carta carta1 = new Carta();
                    carta1.fromJson(jsonObject);

                    System.out.println("---- CARTA ----");

                    System.out.println("COMIDAS:");
                    for (Plato comida : carta1.getComidas()) {
                        System.out.println("Nombre: " + comida.getNombre() + " | Precio: " + comida.getPrecio() +
                                " | Descripción: " + comida.getDescripcion() + " | Categoría: " + comida.getCategoria());
                    }

                    System.out.println("BEBIDAS:");
                    for (Bebida bebida : carta1.getBebidas()) {
                        System.out.println("Nombre: " + bebida.getNombre() + " | Precio: " + bebida.getPrecio() +
                                " | Tipo de bebida: " + bebida.getTipo());
                    }

                    System.out.println("-----------------\n");
                }
            } else {
                System.out.println("No se encontraron elementos en la carta.");
            }

        } catch (JSONException e) {
            System.out.println("No se ha podido leer el archivo de la carta.");
            e.printStackTrace();
        }
    }

    public static void mostrarReservasPorCliente(String rutaArchivoJson, String email) {
        try {
            JSONArray arregloReservas = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloReservas.length() > 0) {
                System.out.println("\n--- TUS RESERVAS ---");

                boolean reservasEncontradas = false;

                for (int i = 0; i < arregloReservas.length(); i++) {
                    JSONObject jsonObject = arregloReservas.getJSONObject(i);

                    Reserva reserva = new Reserva();
                    reserva.fromJson(jsonObject);

                    if (reserva.getCliente().getEmail().equalsIgnoreCase(email)){
                        mostrarDetallesReserva(reserva);
                        reservasEncontradas = true;
                    }
                }

                if (!reservasEncontradas) {
                    System.out.println("No se encontraron reservas para el cliente especificado.");
                }
            } else {
                System.out.println("No se encontraron reservas en el archivo.");
            }
        } catch (JSONException e) {
            System.out.println("No se ha podido leer el archivo.");
            e.printStackTrace();
        }
    }

    private static void mostrarDetallesReserva(Reserva reserva) {
        System.out.println("Fecha: " + reserva.getFecha() +
                " | Mesa Número: " + reserva.getMesa().getNumero() +
                " | Capacidad: " + reserva.getMesa().getCapacidad() +
                " | Cliente: " + reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido());
    }

}
