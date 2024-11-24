package ClasesGestoras;
import Clases.Usuarios.*;
import Excepciones.AutenticacionException;
import Excepciones.EmailDuplicadoException;
import java.util.HashSet;
import java.util.Set;

public class Usuarios<T extends Usuario> {
    private Set<T> listaUsuarios;

    public Usuarios()  {
        this.listaUsuarios = new HashSet<>();
    }

    public Set<T> getListaUsuarios() {
        return listaUsuarios;
    }

    public void registrarUsuarioHarcodeado(T usuario) throws EmailDuplicadoException {
        if (!listaUsuarios.add(usuario)) {
            throw new EmailDuplicadoException("El usuario con este email ya está registrado.");
        }
    }

    public String registrarUsuario(Restaurante restaurante,
                                   String nombre,
                                   String apellido,
                                   String email,
                                   String contrasena,
                                   String direccion,
                                   String telefono,
                                   int opcion) {
        String msj = "Usuario creado con exito!";
        try {
            for (T usuario : listaUsuarios) {
                if (usuario.getEmail().equals(email)) {
                    throw new IllegalArgumentException("⚠️ El email ya está registrado: " + email);
                }
            }

            T nuevoUsuario = null;

            switch (opcion) {
                case 1:
                    nuevoUsuario = (T) new Administrador(nombre, apellido, email, contrasena);
                    break;

                case 2:
                    nuevoUsuario = (T) new Cocinero(nombre, apellido, email, contrasena);
                    break;

                case 3:
                    if (direccion == null || telefono == null) {
                        throw new IllegalArgumentException("⚠️ Para crear un cliente, se requiere dirección y teléfono.");
                    }
                    nuevoUsuario = (T) new Cliente(nombre, apellido, email, contrasena, direccion, telefono);
                    break;
                case 4:
                    nuevoUsuario = (T) new Camarero(nombre, apellido, email, contrasena);
                    break;
                default:
                    throw new IllegalArgumentException("⚠️ Tipo de usuario no válido: ");
            }

            restaurante.getUsuarios().getListaUsuarios().add(nuevoUsuario);

        } catch (IllegalArgumentException e) {
            msj = e.getMessage();
        }
        return msj;
    }

    public String loguearUsuario(String email, String contrasenia) throws AutenticacionException {
        String msj = "";
        try {
            for (T usuario : listaUsuarios) {
                if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getContrasenia().equalsIgnoreCase(contrasenia)) {
                    msj = usuario.getClass().getSimpleName();
                    return msj;
                }
            }
            throw new AutenticacionException("⚠️ Email o contraseña incorrectos");
        } catch (AutenticacionException e) {
            System.out.println(e.getMessage());
            msj = "⚠️ Autenticación fallida";
        }
        return msj;
    }

    public String mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            return "⚠️ No hay usuarios registrados.";
        }

        StringBuilder resultado = new StringBuilder();
        for (T usuario : listaUsuarios) {
            resultado.append(usuario.toString()).append("\n");
        }

        return resultado.toString();
    }

    public Cliente buscarPorId(int id) {
        try {
            Cliente clienteAux = null;
            for (Usuario cliente : listaUsuarios) {
                if (cliente.getId() == id && cliente.getClass().getSimpleName().equalsIgnoreCase("cliente")) {
                    clienteAux = (Cliente) cliente;
                    break;
                }
            }
            if (clienteAux == null) {
                throw new RuntimeException("⚠️ No se encuentra el id: " + id + " o el usuario no es un cliente.");
            }
            return clienteAux;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Usuario buscarPorEmail(String email){
        Usuario usuario = new Usuario();
        for (Usuario u : listaUsuarios){
            if (u.getEmail().equals(email)){
                usuario = u;
            }
        }
        if (usuario == null){
            throw new RuntimeException("⚠️ No se encuentra el email");
        }
        return usuario;
    }
}



