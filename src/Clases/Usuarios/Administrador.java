package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Cuenta;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import ClasesGestoras.Reservas;
import Excepciones.DescuentoException;

public class Administrador extends Usuario{

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    @Override
    public boolean cambiarContrasenia() {return false;}

    public boolean gestionarMenu(int opcion , Carta carta, Plato plato, Bebida bebida) {
        switch (opcion) {
            case 1: // Agregar un plato
                carta.agregarComida(plato);
                break;
            case 2: // Agregar una bebida
                carta.agregarBebida(bebida);
                break;
            case 3: // Eliminar un plato
                carta.eliminarComida(plato);
                break;
            case 4: // Eliminar una bebida
                carta.eliminarBebida(bebida);
                break;
            default:
                throw new IllegalArgumentException("Opción no válida para gestionar la carta");
        }
        return false;
    }

    public void crearPromocion(int descuento,Cuenta cuenta) throws DescuentoException {
        if(descuento>0){
            cuenta.setDescuento(descuento);
        }else{
            throw new DescuentoException("El descuento no puede ser negativo");
        }
    }

    public String verReservas(Reservas res){return res.verReservas();}
}
