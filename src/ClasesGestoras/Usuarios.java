package ClasesGestoras;
import Clases.Usuarios.Cliente;
import Clases.Usuarios.Usuario;
import Excepciones.AutenticacionException;
import Excepciones.EmailDuplicadoException;
import Excepciones.UsuarioNoEncontradoException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashSet;
import java.util.Set;

public class Usuarios implements IJson {
    Set<Usuario> listaUsuarios;

    public Usuarios()  {
        this.listaUsuarios = new HashSet<>();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try{
            JSONArray usuariosJson = new JSONArray();
            for(Usuario u : listaUsuarios){
                usuariosJson.put(u.toJson());
            }
            obj.put("usuarios", usuariosJson);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        Usuario u = null;
        try {
            JSONArray usuariosJson = json.getJSONArray("usuarios");
            for (int i = 0; i < usuariosJson.length(); i++) {
                u.fromJson(usuariosJson.getJSONObject(i));
                listaUsuarios.add(u);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
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



