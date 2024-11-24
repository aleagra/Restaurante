package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorReserva;
import Interfaces.IMostrarCarta;
import Interfaces.IMostrarMesas;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;

public class Administrador extends Usuario implements IGestorReserva, IMostrarCarta, IMostrarMesas {

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    public void gestionarMenu(int opcion , Mesas mesas, Mesa m, int nroMesa, Carta carta, Plato plato, Bebida bebida, String nombre) throws JSONException {
        switch (opcion) {
        case 1:
            System.out.println(carta.agregarComida(plato));
            break;
        case 2:
            System.out.println(carta.agregarBebida(bebida));
            break;
        case 3:
            System.out.println(carta.eliminarComida(nombre));
            break;
        case 4:
            System.out.println(carta.eliminarBebida(nombre));
            break;
        case 5:
            System.out.println(mesas.addMesa(m));
            break;
        case 6:
            System.out.println(mesas.deleteMesa(nroMesa));
            break;
        default:
            throw new IllegalArgumentException("⚠️ Opción no válida para gestionar la carta");
    }
}
    @Override
    public String toString() {
        return "ADMINISTRADOR: " +
                " ID: " + id +
                " NOMBRE: " + nombre + '\'' +
                " APELLIDO: " + apellido + '\'' +
                " EMAIL: " + email;
    }

    @Override
    public String obtenerReservas() {
        return Reservas.mostrarReservas();
    }

    @Override
    public String mostrarCarta(String json) {
        return Carta.mostrarCartaDesdeJson(json);
    }

    @Override
    public String mostrarMesas(String json) {
        return Mesas.mostrarTodasLasMesas(json);
    }
}