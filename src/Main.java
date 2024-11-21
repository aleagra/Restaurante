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
        inicializarRestaurante(restaurante);

        String email = "claudia.vega@mail.com";
        String contra = "client456";

        restaurante.getUsuarios().registrarUsuario();

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
                    menuPrincipal.getMenuAdmin().mostrarMenu(scanner,admin, restaurante.getCarta(),
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
                   menuPrincipal.getMenuCamarero().mostrarMenu(scanner,camareroAux,
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



}