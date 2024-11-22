package Menus;
import Clases.Usuarios.*;
import ClasesGestoras.Restaurante;
import java.util.List;
public class MenuPrincipal {
    MenuCamarero menuCamarero;
    MenuCliente menuCliente;
    MenuAdmin menuAdmin;
    MenuCocinero menuCocinero ;

    public MenuPrincipal() {
        this.menuCamarero = new MenuCamarero();
        this.menuCliente =new MenuCliente();
        this.menuAdmin = new MenuAdmin();
        this.menuCocinero = new MenuCocinero();
    }

    public MenuCamarero getMenuCamarero() {
        return menuCamarero;
    }

    public MenuCliente getMenuCliente() {
        return menuCliente;
    }

    public MenuAdmin getMenuAdmin() {
        return menuAdmin;
    }

    public MenuCocinero getMenuCocinero() {
        return menuCocinero;
    }

    public static void inicializarRestaurante(Restaurante restaurante) {
        List<Usuario> usuarios = List.of(
                new Camarero("Pedro", "Fernández", "b", "b"),
                new Administrador("Lucía", "Gómez", "a", "a"),
                new Cocinero("Marcos", "Rivas", "m", "m"),
                new Cocinero("Elena", "Suárez", "elena.suarez@mail.com", "chef001"),
                new Cliente("Jorge", "Díaz", "jorge.diaz@mail.com", "client123", "Av. Libertad 123", "1122334455"),
                new Cliente("Claudia", "Vega", "claudia.vega@mail.com", "client456", "Calle Primavera 456", "2233445566"),
                new Cliente("Andrés", "Soto", "andres.soto@mail.com", "", "Pasaje Azul 789", "3344556677"),
                new Cliente("Valeria", "Campos", "valeria.campos@mail.com", "client001", "Bulevar Verde 321", "4455667788")
        );
        usuarios.forEach(restaurante.getUsuarios()::registrarUsuarioHarcodeado);
        System.out.println("Usuarios inicializados y registrados en el sistema.");
    }
}
