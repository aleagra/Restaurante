package Clases.Usuarios;


import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorPedidos;
import Interfaces.IGestorReserva;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Cliente extends Usuario implements IGestorReserva, IGestorPedidos, IJson {
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

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try {

            json.put("Nombre",this.nombre);
            json.put("Apellido",this.apellido);
            json.put("Email",this.email);
            json.put("Contrasenia",this.contrasenia);
            json.put("Telefono",this.telefono);
            json.put("Direccion",this.direccion);

        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try{
            this.nombre = json.getString("Nombre");
            this.apellido = json.getString("Apellido");
            this.email = json.getString("Email");
            this.contrasenia = json.getString("Contrasenia");
            this.telefono = json.getString("Telefono");
            this.direccion = json.getString("Direccion");

        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
    }

}