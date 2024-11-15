package Clases;

import java.util.List;

public class Cliente extends Usuario{
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String telefono, String direccion) {
        super(nombre, apellido, email, contrasenia);
        this.telefono = telefono;
        this.direccion = direccion;
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
    public boolean cambiarContrasenia() {
        return false;
    }

    public void hacerReserva(){

    }

    public void hacerPedido(){

    }

    public String consultarMenu(){
        return null;
    }



}
