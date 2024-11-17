package Clases.Usuarios;

public class Administrador extends Usuario{

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    @Override
    public boolean cambiarContrasenia() {
        return false;
    }

    public void gestionarMenu(){

    }

    public void crearPromocion(){

    }

    public void verReservas(){

    }
}
