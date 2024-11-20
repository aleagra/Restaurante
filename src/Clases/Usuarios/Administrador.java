package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorReserva;

public class Administrador extends Usuario implements IGestorReserva {

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

public boolean gestionarMenu(int opcion , Mesas mesas, Mesa m, Carta carta, Plato plato, Bebida bebida, String nombre) {
    switch (opcion) {
        case 1:
            carta.agregarComida(plato);
            break;
        case 2:
            carta.agregarBebida(bebida);
            break;
        case 3:
            carta.eliminarComida(nombre);
            break;
        case 4:
            carta.eliminarBebida(nombre);
            break;
        case 5:
            mesas.addMesa(m);
            break;
        case 6:
            mesas.deleteMesa(m);
            break;
        default:
            throw new IllegalArgumentException("Opción no válida para gestionar la carta");
    }
    return true;
}

    @Override
    public String cambiarContrasenia(String contraActual, String contraNueva) {
        String msj = "Contraseña cambiada.";
        if(contraActual.equals(this.contrasenia)){
            setContrasenia(contraNueva);
            return msj;
        }
        throw new ContraseniaException("La contraseña no se pudo cambiar.");
    }

    @Override
    public String obtenerReservas(Reservas res) {
        return res.verReservas();
    }
}