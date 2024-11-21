package ClasesGestoras;
import Clases.Usuarios.Cliente;
import Clases.Usuarios.Usuario;
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

    public void registrarUsuario(Usuario usuario) throws EmailDuplicadoException {
        if (!listaUsuarios.add(usuario)) {
            throw new EmailDuplicadoException("El usuario con este email ya está registrado.");
        }
        System.out.println("Usuario registrado exitosamente.");
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
}



