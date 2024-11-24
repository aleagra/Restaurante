package Clases.Usuarios;


import ClasesGestoras.Carta;
import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorPedidos;
import Interfaces.IGestorReserva;
import Interfaces.IJson;
import Interfaces.IMostrarCarta;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.catalog.Catalog;

public class Cliente extends Usuario implements IGestorPedidos, IJson,IGestorReserva, IMostrarCarta {
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String telefono, String direccion) {
        super(nombre, apellido, email, contrasenia);
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Cliente(){
    }

    @Override
    public String toString() {
        return "CLIENTE: " + super.toString() +
                " TELEFONO: " + telefono + '\'' +
                " DIRECCION: "  + direccion;
    }

    @Override
    public String obtenerReservas() {
        return Reservas.mostrarReservasPorCliente(this.email);
    }

    @Override
    public String obtenerPedidos(Pedidos pedidos) {
        return pedidos.mostrarPedidosPorCliente(email);
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try {
            json.put("Id",this.id);
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
            this.id = json.getInt("Id");
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

    @Override
    public String mostrarCarta(String json) {
        return Carta.mostrarCartaDesdeJson(json);
    }
}