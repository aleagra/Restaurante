package ClasesGestoras;
import Clases.Usuario;
import Excepciones.AutenticacionException;
import Excepciones.EmailDuplicadoException;
import Excepciones.UsuarioNoEncontradoException;

import java.util.HashSet;
import java.util.Set;

public class Usuarios {
    Set<Usuario> listaUsuarios;

    public Usuarios() {
        this.listaUsuarios = new HashSet<>();
    }

    public void registrarUsuario(Usuario usuario) throws EmailDuplicadoException {
        if (!listaUsuarios.add(usuario)) {
            throw new EmailDuplicadoException("El usuario con este email ya está registrado.");
        }
        System.out.println("Usuario registrado exitosamente.");
    }

    public void loguearUsuario(String email, String contrasenia) throws AutenticacionException {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email) && usuario.getContrasenia().equals(contrasenia)) {
                System.out.println("Usuario logueado exitosamente");
                return;
            }
        }
        throw new AutenticacionException("Email o contraseña incorrectos");
    }


    public String eliminarUsuario(String email) throws UsuarioNoEncontradoException {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email)) {
                listaUsuarios.remove(usuario);
                return "Usuario eliminado exitosamente.";
            }
        }
        throw new UsuarioNoEncontradoException("El usuario con este email no existe.");
    }

    public String mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            return "No hay usuarios registrados.";
        }

        StringBuilder resultado = new StringBuilder();
        for (Usuario usuario : listaUsuarios) {
            resultado.append(usuario.toString()).append("\n");
        }

        return resultado.toString();
    }

    public String filtrarPorClase(String nombreClase) {
        StringBuilder resultado = new StringBuilder();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getClass().getSimpleName().equalsIgnoreCase(nombreClase)) {
                resultado.append(usuario.toString()).append("\n");
            }
        }

        return !resultado.isEmpty() ? resultado.toString() : "No se encontraron usuarios de la clase " + nombreClase;
    }
}



