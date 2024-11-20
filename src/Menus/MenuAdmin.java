package Menus;

import Clases.Gestion.Bebida;
import Clases.Gestion.Plato;
import Clases.Usuarios.Administrador;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;
import Excepciones.MenuException;

import java.util.Scanner;

public class MenuAdmin {

    public void mostrarMenu(Administrador administrador, Carta c, Reservas r) throws MenuException {
        int opcion;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n========== MENU ADMINISTRADOR ==========");
            System.out.println("1. Gestionar el menú");
            System.out.println("2. Ver reservas");
            System.out.println("3. Ver platos");
            System.out.println("4. Ver bebidas");
            System.out.println("5. Cambiar contraseña");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestionMenu(administrador, sc, c);
                    break;
                case 2:
                    System.out.println("\n--- Reservas ---");
                    System.out.println(administrador.obtenerReservas(r));
                    break;
                case 3:
                    System.out.println("🍽️ Platos:");
                    System.out.println(c.mostrarComidas());
                    break;
                case 4:
                    System.out.println("🍹 Bebidas:");
                    System.out.println(c.mostrarBebidas());
                    break;
                case 5:
                    System.out.println("\n--- Cambio de contraseña ---");
                    System.out.print("Ingrese la contraseña actual: ");
                    String contraActual = sc.nextLine();
                    System.out.print("Ingrese la nueva contraseña: ");
                    String contraNueva = sc.nextLine();
                    System.out.println(administrador.cambiarContrasenia(contraActual, contraNueva));
                    break;

                case 0:
                    System.out.println("\n👋 Finalizando sesión del administrador...");
                    break;

                default:
                    System.out.println("\n⚠️ Opción inválida. Por favor, intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }

    public void gestionMenu(Administrador administrador, Scanner sc, Carta c) {
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
                        case 1 -> pl.setCategoria(TipoDePlato.ENTRANTE);
                        case 2 -> pl.setCategoria(TipoDePlato.PRINCIPAL);
                        case 3 -> pl.setCategoria(TipoDePlato.POSTRE);
                        default -> System.out.println("⚠️ Categoría inválida. Selección por defecto: 'Principal'.");
                    }

                    if (administrador.gestionarMenu(1, c, pl, null, null)) {
                        System.out.println("✅ Plato agregado correctamente.");
                    } else {
                        throw new MenuException("⚠️ El plato no pudo ser agregado.");
                    }
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
                        case 1 -> beb.setTipo(TipoDeBebida.CON_ALCOHOL);
                        case 2 -> beb.setTipo(TipoDeBebida.SIN_ALCOHOL);
                        default -> System.out.println("⚠️ Tipo inválido. Selección por defecto: 'Sin alcohol'.");
                    }

                    if (administrador.gestionarMenu(2, c, null, beb, null)) {
                        System.out.println("✅ Bebida agregada exitosamente.");
                    } else {
                        throw new MenuException("⚠️ La bebida no pudo ser agregada.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Eliminar un plato ---");
                    System.out.print("Ingrese el nombre del plato a eliminar: ");
                    String nombrePlato = sc.nextLine();
                    Plato plato = new Plato();
                    plato.setNombre(nombrePlato);

                    if (administrador.gestionarMenu(3, c, plato, null, nombrePlato)) {
                        System.out.println("✅ Plato eliminado correctamente.");
                    } else {
                        throw new MenuException("⚠️ El plato no pudo ser eliminado.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Eliminar una bebida ---");
                    System.out.print("Ingrese el nombre de la bebida a eliminar: ");
                    String nombreBebida = sc.nextLine();
                    Bebida bebida = new Bebida();
                    bebida.setNombre(nombreBebida);

                    if (administrador.gestionarMenu(4, c, null, bebida, nombreBebida)) {
                        System.out.println("✅ Bebida eliminada correctamente.");
                    } else {
                        throw new MenuException("⚠️ La bebida no pudo ser eliminada.");
                    }
                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    System.out.println("\n🔙 Volviendo al menú principal...");
            }
        } while (opcion != 0);
    }
}
