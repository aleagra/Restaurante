import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import Clases.Usuarios.*;
import ClasesGestoras.Carta;
import ClasesGestoras.Restaurante;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;
import JSONUtiles.JSONUtiles;
import Menus.MenuAdmin;
import Menus.MenuCamarero;
import Menus.MenuCliente;
import Menus.MenuCocinero;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        MenuCamarero menuCamarero = new MenuCamarero();
        MenuCliente menuCliente = new MenuCliente();
        MenuAdmin menuAdmin = new MenuAdmin();
        MenuCocinero menuCocinero = new MenuCocinero();
        JSONArray reservas = new JSONArray();
        JSONArray carta = new JSONArray();

        Plato plato1 = new Plato("Ensalada César", "Lechuga, crutones, queso parmesano y aderezo César", 8.50, TipoDePlato.ENTRANTE);
        Plato plato2 = new Plato("Bife de chorizo", "Carne de res con guarnición de papas", 15.00, TipoDePlato.ENTRANTE);
        Plato plato3 = new Plato("Tarta de manzana", "Postre clásico con base de manzana caramelizada", 5.00, TipoDePlato.POSTRE);
        Plato plato4 = new Plato("Sopa de tomate", "Sopa caliente con tomates frescos y hierbas", 6.00, TipoDePlato.PRINCIPAL);
        Bebida bebida1 = new Bebida("Agua Mineral", 2.00, TipoDeBebida.SIN_ALCOHOL);
        Bebida bebida2 = new Bebida("Coca-Cola", 3.00, TipoDeBebida.SIN_ALCOHOL);
        Bebida bebida3 = new Bebida("Vino Tinto", 10.00, TipoDeBebida.CON_ALCOHOL);
        Bebida bebida4 = new Bebida("Café Expreso", 4.00, TipoDeBebida.SIN_ALCOHOL);
        Camarero camarero = new Camarero("Pedro", "Fernández", "pedro.fernandez@mail.com", "cam123");
        Administrador administrador = new Administrador("Lucía", "Gómez", "lucia.gomez@mail.com", "admin456");
        Cocinero cocinero1 = new Cocinero("Marcos", "Rivas", "marcos.rivas@mail.com", "cook789");
        Cocinero cocinero2 = new Cocinero("Elena", "Suárez", "elena.suarez@mail.com", "chef001");
        Cliente cliente1 = new Cliente("Jorge", "Díaz", "jorge.diaz@mail.com", "client123", "Av. Libertad 123", "1122334455");
        Cliente cliente2 = new Cliente("Claudia", "Vega", "claudia.vega@mail.com", "client456", "Calle Primavera 456", "2233445566");
        Cliente cliente3 = new Cliente("Andrés", "Soto", "andres.soto@mail.com", "client789", "Pasaje Azul 789", "3344556677");
        Cliente cliente4 = new Cliente("Valeria", "Campos", "valeria.campos@mail.com", "client001", "Bulevar Verde 321", "4455667788");


        restaurante.getCarta().agregarBebida(bebida1);
        restaurante.getCarta().agregarBebida(bebida2);
        restaurante.getCarta().agregarBebida(bebida3);
        restaurante.getCarta().agregarBebida(bebida4);

        restaurante.getCarta().agregarComida(plato1);
        restaurante.getCarta().agregarComida(plato2);
        restaurante.getCarta().agregarComida(plato3);
        restaurante.getCarta().agregarComida(plato4);

        try {
            carta.put( restaurante.getCarta().toJson());
            JSONUtiles.grabarUnJson(carta, "carta.json");

        }catch (JSONException e){
            e.printStackTrace();
        }


        try {
            JSONArray arregloCarta = new JSONArray(JSONUtiles.leerUnJson("carta.json"));

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

        restaurante.getUsuarios().registrarUsuario(camarero);
        restaurante.getUsuarios().registrarUsuario(administrador);
        restaurante.getUsuarios().registrarUsuario(cocinero1);
        restaurante.getUsuarios().registrarUsuario(cocinero2);
        restaurante.getUsuarios().registrarUsuario(cliente1);
        restaurante.getUsuarios().registrarUsuario(cliente2);
        restaurante.getUsuarios().registrarUsuario(cliente3);
        restaurante.getUsuarios().registrarUsuario(cliente4);

        String logueo = restaurante.getUsuarios().loguearUsuario("lucia.gomez@mail.com", "admin456");
        Mesa mesa = new Mesa();

        switch (logueo){
            case "Cliente":
                menuCliente.mostrarMenu(scanner, restaurante.getMesas(), cliente1, restaurante.getReservas(), restaurante.getPedidos(),restaurante.getCarta(),reservas);
                break;
            case "Administrador":
                menuAdmin.mostrarMenu(administrador, restaurante.getCarta(), restaurante.getReservas(), restaurante.getMesas(), mesa, restaurante.getUsuarios());
                break;
            case "Cocinero":
                menuCocinero.mostrarMenuCocinero(restaurante.getPedidos(), scanner,cocinero1);
                break;
            case "Camarero":
                menuCamarero.mostrarMenu(camarero,restaurante.getMesas(),restaurante.getPedidos(),restaurante.getUsuarios(),restaurante.getCarta());
                break;
        }
    }
}