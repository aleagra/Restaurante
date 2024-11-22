package Clases.Usuarios;
import Excepciones.ContraseniaException;

import java.util.Objects;

public class Usuario{
    private static int contador=1;
    public int id;
    public String nombre;
    public String apellido;
    public String email;
    public String contrasenia;

    public Usuario(String nombre, String apellido, String email, String contrasenia) {
        this.id = contador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    public Usuario(){

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + " " +
                " NOMBRE: " + nombre + " " +
                " APELLIDO: " + apellido + " " +
                " EMAIL: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    public String cambiarContrasenia(String contraActual, String contraNueva) {
        try {
            if (!contraActual.equals(this.contrasenia)) {
                throw new ContraseniaException("⚠️ La contraseña actual no es correcta.");
            }
            if (contraActual.equals(contraNueva)) {
                throw new ContraseniaException("⚠️ La nueva contraseña no puede ser igual a la actual.");
            }
            setContrasenia(contraNueva);
            return "✅ Contraseña cambiada.";
        } catch (ContraseniaException e) {
            return e.getMessage();
        }
    }
}
