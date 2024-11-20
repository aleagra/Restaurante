import Clases.Gestion.Bebida;
import Clases.Gestion.Cuenta;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import Clases.Usuarios.*;
import ClasesGestoras.Restaurante;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;
import Menus.MenuAdmin;
import Menus.MenuCamarero;
import Menus.MenuCliente;
import Menus.MenuCocinero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        MenuCamarero menuCamarero = new MenuCamarero();
        MenuCliente menuCliente = new MenuCliente();
        MenuAdmin menuAdmin = new MenuAdmin();
        MenuCocinero menuCocinero = new MenuCocinero();

        Mesa mesa1 = new Mesa(2);
        Mesa mesa2 = new Mesa(4);
        Mesa mesa3 = new Mesa(6);
        Mesa mesa4 = new Mesa(8);
        Plato plato1 = new Plato("Ensalada César", "Lechuga, crutones, queso parmesano y aderezo César", 8.50, TipoDePlato.ENTRANTE);
        Plato plato2 = new Plato("Bife de chorizo", "Carne de res con guarnición de papas", 15.00, TipoDePlato.ENTRANTE);
        Plato plato3 = new Plato("Tarta de manzana", "Postre clásico con base de manzana caramelizada", 5.00, TipoDePlato.POSTRE);
        Plato plato4 = new Plato("Sopa de tomate", "Sopa caliente con tomates frescos y hierbas", 6.00, TipoDePlato.PRINCIPAL);
        Bebida bebida1 = new Bebida("Agua Mineral", 2.00, TipoDeBebida.SIN_ALCOHOL);
        Bebida bebida2 = new Bebida("Coca-Cola", 3.00, TipoDeBebida.SIN_ALCOHOL);
        Bebida bebida3 = new Bebida("Vino Tinto", 10.00, TipoDeBebida.CON_ALCOHOL);
        Bebida bebida4 = new Bebida("Café Expreso", 4.00, TipoDeBebida.SIN_ALCOHOL);
        Camarero camarero = new Camarero("Ana", "García", "ana.garcia@mail.c", "password123");
        Administrador administrador = new Administrador("Luis", "Martínez", "luis.martinez@mail.com", "qwerty456");
        Cocinero cocinero1 = new Cocinero("Sofía", "López", "sofia.lopez@mail.com", "abcd7890");
        Cocinero cocinero2 = new Cocinero("Carlos", "Pérez", "carlos.perez@mail.com", "securepass01");
        Cliente cliente1 = new Cliente("Ana", "García", "ana.garcia@mail.como", "password123", "Av. Siempre Viva 742", "123456789");
        Cliente cliente2 = new Cliente("Luis", "Martínez", "luis.martinez@mail.comu", "qwerty456", "Calle Falsa 123", "987654321");
        Cliente cliente3 = new Cliente("Sofía", "López", "sofia.lopez@mail.comk", "abcd7890", "Boulevard del Sol 456", "456123789");
        Cliente cliente4 = new Cliente("Carlos", "Pérez", "carlos.perez@mail.coml", "securepass01", "Camino Real 89", "789456123");

        restaurante.getMesas().addMesa(mesa1);
        restaurante.getMesas().addMesa(mesa2);
        restaurante.getMesas().addMesa(mesa3);

        restaurante.getCarta().agregarBebida(bebida1);
        restaurante.getCarta().agregarBebida(bebida2);
        restaurante.getCarta().agregarBebida(bebida3);
        restaurante.getCarta().agregarBebida(bebida4);

        restaurante.getCarta().agregarComida(plato1);
        restaurante.getCarta().agregarComida(plato2);
        restaurante.getCarta().agregarComida(plato3);
        restaurante.getCarta().agregarComida(plato4);

        restaurante.getUsuarios().registrarUsuario(camarero);
        restaurante.getUsuarios().registrarUsuario(administrador);
        restaurante.getUsuarios().registrarUsuario(cocinero1);
        restaurante.getUsuarios().registrarUsuario(cocinero2);
        restaurante.getUsuarios().registrarUsuario(cliente1);
        restaurante.getUsuarios().registrarUsuario(cliente2);
        restaurante.getUsuarios().registrarUsuario(cliente3);
        restaurante.getUsuarios().registrarUsuario(cliente4);

        String logueo = restaurante.getUsuarios().loguearUsuario("sofia.lopez@mail.com", "abcd7890");

        switch (logueo){
            case "Cliente":
                menuCliente.MostrarMenu(scanner, restaurante.getMesas(), cliente1, restaurante.getReservas(), restaurante.getPedidos(),restaurante.getCarta());
                break;
            case "Administrador":
                Cuenta cuenta = new Cuenta();
                menuAdmin.mostrarMenu(administrador,cuenta, restaurante.getCarta(), restaurante.getReservas());
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