package Clases.Usuarios;

import Clases.Gestion.Pedido;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;
import Excepciones.ContraseniaException;
import Excepciones.PedidoException;
import Interfaces.IGestorPedidos;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Cocinero extends Usuario implements IGestorPedidos, IJson {

    public Cocinero(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    public int actualizarEstadoPedido(Pedidos pedidos, int nroPedido, EstadoPedido estado){
        for(Pedido p: pedidos.getPedidos()){
            if(p.getNumeroPedido() == nroPedido){
                return p.actualizarEstadoPedido(estado);
            }
        }
        throw new PedidoException("No se encontro ningun pedido con eso nro!");
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
    public String obtenerPedidos(Pedidos pedidos) {
        return pedidos.mostrarPedidos();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try {

            json.put("Nombre",this.nombre);
            json.put("Apellido",this.apellido);
            json.put("Email",this.email);
            json.put("Contrasenia",this.contrasenia);

        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json)throws JSONException {
        try{
            this.nombre = json.getString("Nombre");
            this.apellido = json.getString("Apellido");
            this.email = json.getString("Email");
            this.contrasenia = json.getString("Contrasenia");
        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
    }


}
