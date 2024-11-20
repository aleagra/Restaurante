package Clases.Usuarios;


import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorPedidos;
import Interfaces.IGestorReserva;

public class Cliente extends Usuario implements IGestorReserva, IGestorPedidos {
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String telefono, String direccion) {
        super(nombre, apellido, email, contrasenia);
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Cliente(){
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString() +
                "telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
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
        return res.mostrarReservasPorCliente(email);
    }

    @Override
    public String obtenerPedidos(Pedidos pedidos) {
        return pedidos.mostrarPedidosPorCliente(email);
    }
}