package ClasesGestoras;
import Clases.Usuarios.*;
import Excepciones.AutenticacionException;
import Excepciones.EmailDuplicadoException;
import Excepciones.UsuarioNoEncontradoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashSet;
import java.util.Set;

public class Usuarios {
    Set<Usuario> listaUsuarios;

    public Usuarios()  {
        this.listaUsuarios = new HashSet<>();
    }

    public Set<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public void registrarUsuarioHarcodeado(Usuario usuario) throws EmailDuplicadoException {
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
                                 String telefono,int opcion) {
        // Comprobar si el email ya está registrado
        for(Usuario usuario: listaUsuarios) {
            if(usuario.getEmail().equals(email)) {
                throw new IllegalArgumentException("El email ya está registrado: " + email);
            }
        }

        Usuario nuevoUsuario = null;

        // Crear instancia del usuario según el tipo
        switch (opcion) {
            case 1:
                nuevoUsuario = new Administrador(nombre, apellido, email, contrasena);
                break;

            case 2:
                nuevoUsuario = new Cocinero(nombre, apellido, email, contrasena);
                break;

            case 3:
                if (direccion == null || telefono == null) {
                    throw new IllegalArgumentException("Para crear un cliente, se requiere dirección y teléfono.");
                }
                nuevoUsuario = new Cliente(nombre, apellido, email, contrasena, direccion, telefono);
                break;
            case 4:
                nuevoUsuario = new Camarero(nombre,apellido,email,contrasena);
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " );
        }

        restaurante.getUsuarios().getListaUsuarios().add(nuevoUsuario);

       return "Usuario creado con exito!";
    }

    public String loguearUsuario(String email, String contrasenia) throws AutenticacionException {
        String msj="";
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email) && usuario.getContrasenia().equals(contrasenia)) {
               msj=usuario.getClass().getSimpleName();
                return msj;
            }
        }
        throw new AutenticacionException("Email o contraseña incorrectos");
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

    public Cliente buscarPorId(int id){
       Cliente clienteAux = new Cliente();
        for (Usuario cliente:listaUsuarios){
            if (cliente.getId() == id && cliente.getClass().getSimpleName().equalsIgnoreCase("cliente")){
                clienteAux = (Cliente) cliente;
            }
        }
        if (clienteAux == null){
            throw new RuntimeException("No se encuentra el id");
        }
        return clienteAux;
    }

    public Usuario buscarPorEmail(String email){
        Usuario usuario = new Usuario();
        for (Usuario u : listaUsuarios){
            if (u.getEmail().equals(email)){
                usuario = u;
            }
        }
        if (usuario == null){
            throw new RuntimeException("No se encuentra el email");
        }
        return usuario;
    }
}



