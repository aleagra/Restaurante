import Clases.Gestion.Mesa;
import Clases.Usuarios.*;
import ClasesGestoras.Restaurante;
import Menus.*;
import org.json.JSONArray;
import java.util.Scanner;

import static Menus.MenuPrincipal.inicializarRestaurante;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        JSONArray reservas = new JSONArray();
        int opcion = 0;
        inicializarRestaurante(restaurante);


        do {
            System.out.println("MENU");
            System.out.println("1) Registrar Usuario");
            System.out.println("2) Iniciar Sesion");
            System.out.println("0) Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    System.out.println("Ingrese el tipo de usuario");
                    System.out.println("1-Administrador");
                    System.out.println("2-Cocinero");
                    System.out.println("3-Cliente");
                    System.out.println("4-Camarero");
                    int opcion2 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese su email: ");
                    String email = scanner.nextLine();
                    System.out.println("Ingrese su contrasenia: ");
                    String contra = scanner.nextLine();
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese su apellido: ");
                    String apellido = scanner.nextLine();
                    if (opcion2 == 3) {
                        System.out.println("Ingrese su numero de telefono: ");
                        String telefono = scanner.nextLine();

                        System.out.println("Ingrese su direccion: ");
                        String direccion = scanner.nextLine();

                        System.out.println(restaurante.getUsuarios().registrarUsuario(restaurante, nombre, apellido, email, contra, direccion, telefono, opcion2));
                    } else {
                        System.out.println(restaurante.getUsuarios().registrarUsuario(restaurante, nombre, apellido, email, contra, null, null, opcion2));

                    }


                    break;
                case 2:

                    System.out.println("Ingrese su email: ");
                    email = scanner.nextLine();
                    System.out.println("Ingrese su contrasenia: ");
                    contra = scanner.nextLine();
                    String logueo = restaurante.getUsuarios().loguearUsuario(email, contra);
                    Usuario usuario = restaurante.getUsuarios().buscarPorEmail(email);

                    switch (logueo) {
                        case "Cliente": {
                            if (usuario instanceof Cliente cliente) {
                                menuPrincipal.getMenuCliente().mostrarMenu(scanner, restaurante.getMesas(), cliente,
                                        restaurante.getReservas(), restaurante.getPedidos(), reservas);
                            } else {
                                System.out.println("Error: Usuario no coincide con el rol Cliente.");
                            }
                            break;
                        }
                        case "Administrador": {
                            if (usuario instanceof Administrador admin) {
                                menuPrincipal.getMenuAdmin().mostrarMenu(scanner, admin, restaurante.getCarta(),
                                        restaurante.getMesas(), new Mesa(), restaurante.getUsuarios());
                            } else {
                                System.out.println("Error: Usuario no coincide con el rol Administrador.");
                            }
                            break;
                        }
                        case "Cocinero": {
                            if (usuario instanceof Cocinero cocinero) {
                                menuPrincipal.getMenuCocinero().mostrarMenuCocinero(restaurante.getPedidos(), scanner, cocinero);
                            } else {
                                System.out.println("Error: Usuario no coincide con el rol Cocinero.");
                            }
                            break;
                        }
                        case "Camarero": {
                            if (usuario instanceof Camarero camareroAux) {
                                menuPrincipal.getMenuCamarero().mostrarMenu(scanner, camareroAux,
                                        restaurante.getPedidos(), restaurante.getUsuarios(), restaurante.getCarta());
                            } else {
                                System.out.println("Error: Usuario no coincide con el rol Camarero.");
                            }
                            break;
                        }
                        default:
                            System.out.println("Error: Rol desconocido o credenciales incorrectas.");
                            break;
                    }


            }
        }while (opcion != 0);
    }
}