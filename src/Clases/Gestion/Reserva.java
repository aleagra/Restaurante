package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;

public class Reserva implements IJson {
    private String fecha;
    private Mesa mesa;
    private Cliente cliente;

    // Constructor
    public Reserva(Mesa mesa, Cliente cliente) {
        this.fecha = LocalDate.now().toString();
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public Reserva() {
    }

    public String getFecha() {
        return fecha;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fecha=" + fecha +
                ", mesa=" + mesa +
                '}';
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("fecha", fecha);
            obj.put("mesa", mesa.toJson());
            obj.put("cliente", cliente.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            fecha = json.getString("fecha");
            this.mesa = new Mesa();
            this.mesa.fromJson(json.getJSONObject("mesa"));

            this.cliente = new Cliente();
            this.cliente.fromJson(json.getJSONObject("cliente"));
        } catch (JSONException a) {
            a.printStackTrace();
        }
    }
}
