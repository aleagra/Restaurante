package Clases.Usuarios;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Usuario implements IJson {
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

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Usuario.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " NOMBRE: '" + nombre + '\'' +
                " APELLIDO: '" + apellido + '\'' +
                " EMAIL: '" + email + '\'';
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

    public  String cambiarContrasenia(String contraActual, String contraNueva){
        return "";
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try{
            obj.put("Id", id);
            obj.put("Nombre", nombre);
            obj.put("Apellido", apellido);
            obj.put("Email", email);
            obj.put("Contrasenia", contrasenia);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try{
            this.id = json.getInt("Id");
            this.nombre = json.getString("Nombre");
            this.apellido = json.getString("Apellido");
            this.email = json.getString("Email");
            this.contrasenia = json.getString("Contrasenia");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
