package Clases.Usuarios;

public class Cocinero extends Usuario{

    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    @Override
    public boolean cambiarContrasenia() {
        return false;
    }

    public String verPedido(){
        return null;
    }

    public void actualizarEstadoPedido(){

    }
}
