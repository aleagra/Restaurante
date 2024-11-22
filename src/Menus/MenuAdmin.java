package Menus;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import Clases.Gestion.Reserva;
import Clases.Usuarios.Administrador;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import ClasesGestoras.Usuarios;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;
import Excepciones.MenuException;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class MenuAdmin {

    public void mostrarMenu(Scanner sc,Administrador administrador, Carta c, Mesas mesas, Mesa mesa, Usuarios usuarios) throws MenuException, JSONException {
        int opcion;

        do {
            System.out.println("\n========== MENU ADMINISTRADOR ==========");
            System.out.println("1. Gestionar el menú");
            System.out.println("2. Ver reservas");
            System.out.println("3. Ver carta");
            System.out.println("4. Ver mesas");
            System.out.println("5. Ver usuarios");
            System.out.println("6. Cambiar contraseña");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestionMenu(administrador, sc, c, mesas, mesa);
                    break;
                case 2:
                    mostrarTodasLasReservas("reservas.json");
                    break;
                case 3: 
                    MenuCliente.mostrarCartaDesdeJson("carta.json");
                    break;
                case 4:
                    mostrarTodasLasMesas("mesas.json");
                    break;
                case 5:
                    System.out.println(usuarios.mostrarUsuarios());
                    break;
                case 6:
                    System.out.println("\n--- Cambio de contraseña ---");
                    System.out.print("Ingrese la contraseña actual: ");
                    String contraActual = sc.nextLine();
                    System.out.print("Ingrese la nueva contraseña: ");
                    String contraNueva = sc.nextLine();
                    System.out.println(administrador.cambiarContrasenia(contraActual, contraNueva));
                    break;
                default:
                    System.out.println("\n👋 Finalizando sesión del administrador...");
                    break;
            }
        } while (opcion != 0);
    }

    public void gestionMenu(Administrador administrador, Scanner sc, Carta c, Mesas mesas, Mesa mesa) throws JSONException {
        int opcion;

        do {
            System.out.println("\n========== GESTIÓN DEL MENÚ ==========");
            System.out.println("1. Agregar un plato");
            System.out.println("2. Agregar una bebida");
            System.out.println("3. Eliminar un plato");
            System.out.println("4. Eliminar una bebida");
            System.out.println("5. Agregar una mesa");
            System.out.println("6. Eliminar una mesa");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Agregar un plato ---");
                    Plato pl = new Plato();
                    System.out.print("Ingrese el nombre del plato: ");
                    pl.setNombre(sc.nextLine());
                    System.out.print("Ingrese la descripción del plato: ");
                    pl.setDescripcion(sc.nextLine());
                    System.out.print("Ingrese el precio del plato: ");
                    pl.setPrecio(sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Seleccione la categoría:");
                    System.out.println("1. Entrante");
                    System.out.println("2. Principal");
                    System.out.println("3. Postre");
                    int categoria = sc.nextInt();
                    sc.nextLine();

                    switch (categoria) {
                        case 1:
                            pl.setCategoria(TipoDePlato.ENTRANTE);
                            break;
                        case 2:
                            pl.setCategoria(TipoDePlato.PRINCIPAL);
                            break;
                        case 3:
                            pl.setCategoria(TipoDePlato.POSTRE);
                            break;
                        default:
                            System.out.println("⚠️ Categoría inválida. Selección por defecto: 'Principal'.");
                            break;
                    }
                    administrador.gestionarMenu(1,mesas, mesa,0, c, pl, null, null);
                    break;

                case 2:
                    System.out.println("\n--- Agregar una bebida ---");
                    Bebida beb = new Bebida();
                    System.out.print("Ingrese el nombre de la bebida: ");
                    beb.setNombre(sc.nextLine());
                    System.out.print("Ingrese el precio de la bebida: ");
                    beb.setPrecio(sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Seleccione el tipo de bebida:");
                    System.out.println("1. Con alcohol");
                    System.out.println("2. Sin alcohol");
                    int tipoBebida = sc.nextInt();
                    sc.nextLine();

                    switch (tipoBebida) {
                        case 1:
                            beb.setTipo(TipoDeBebida.CON_ALCOHOL);
                            break;
                        case 2:
                            beb.setTipo(TipoDeBebida.SIN_ALCOHOL);
                            break;
                        default:
                            System.out.println("⚠️ Tipo inválido. Selección por defecto: 'Sin alcohol'.");
                            break;
                    }
                   administrador.gestionarMenu(2,mesas, mesa,0, c, null, beb, null);
                    break;

                case 3:
                    System.out.println("\n--- Eliminar un plato ---");
                    System.out.print("Ingrese el nombre del plato a eliminar: ");
                    String nombrePlato = sc.nextLine();
                    Plato plato = new Plato();
                    plato.setNombre(nombrePlato);
                    administrador.gestionarMenu(3, mesas, mesa,0, c, plato, null, nombrePlato);
                    break;
                case 4:
                    System.out.println("\n--- Eliminar una bebida ---");
                    System.out.print("Ingrese el nombre de la bebida a eliminar: ");
                    String nombreBebida = sc.nextLine();
                    Bebida bebida = new Bebida();
                    bebida.setNombre(nombreBebida);
                   administrador.gestionarMenu(4, mesas, mesa,0, c, null, bebida, nombreBebida);
                    break;
                case 5:
                    Mesa m = new Mesa();
                    System.out.println("Ingrese la cantidad de personas: ");
                    int cant = sc.nextInt();
                    m.setCapacidad(cant);
                    administrador.gestionarMenu(5, mesas, m,0, c, null, null, null);
                    break;
                case 6:
                    System.out.println("Ingrese el nro de la mesa: ");
                    int nroMesa = sc.nextInt();
                    sc.nextLine();
                    administrador.gestionarMenu(6, mesas, mesa, nroMesa, c, null, null, null);
                    break;
                default:
                    System.out.println("\n🔙 Volviendo al menú principal...");
            }
        } while (opcion != 0);
    }

    public static void mostrarTodasLasMesas(String rutaArchivoJson) {
        try {
            JSONArray arregloMesas = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloMesas.length() > 0) {
                for (int i = 0; i < arregloMesas.length(); i++) {
                    JSONObject jsonObject = arregloMesas.getJSONObject(i);

                    Mesa mesa = new Mesa();
                    mesa.fromJson(jsonObject);

                    mostrarDetallesMesa(mesa);
                }
            } else {
                System.out.println("⚠️ No se encontraron mesas en el archivo.");
            }
        } catch (JSONException e) {
            System.out.println("⚠️ No se ha podido leer el archivo.");
            e.printStackTrace();
        }
    }

    private static void mostrarDetallesMesa(Mesa mesa) {
        System.out.println("NUMERO: " + mesa.getNumero() +
                " | CAPACIDAD: " + mesa.getCapacidad() +
                " | ESTADO: " + mesa.getEstadoMesa());
    }

    public static void mostrarTodasLasReservas(String rutaArchivoJson) {
        try {
            JSONArray arregloReservas = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloReservas.length() > 0) {
                System.out.println("\n--- Reservas ---");

                for (int i = 0; i < arregloReservas.length(); i++) {
                    JSONObject jsonObject = arregloReservas.getJSONObject(i);

                    Reserva reserva = new Reserva();
                    reserva.fromJson(jsonObject);

                    mostrarDetallesReserva(reserva);
                }
            } else {
                System.out.println("⚠️ No se encontraron reservas en el archivo.");
            }
        } catch (JSONException e) {
            System.out.println("⚠️ No se ha podido leer el archivo.");
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
