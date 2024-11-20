package ClasesGestoras;

import Clases.Gestion.Mesa;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurante {
    Carta carta;
    Mesas mesas;
    Pedidos pedidos;
    Reservas reservas;
    Usuarios usuarios;

    public Restaurante() {
        this.carta = new Carta();
        this.mesas = new Mesas();
        this.pedidos = new Pedidos();
        this.reservas = new Reservas();
        this.usuarios = new Usuarios();
    }

    public Carta getCarta() {
        return carta;
    }

    public Mesas getMesas() {
        return mesas;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public Reservas getReservas() {
        return reservas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try{
            json.put("carta", carta.toJson());
            json.put("mesas", mesas.toJson());
            json.put("pedidos", pedidos.toJson());
            json.put("reservas", reservas.toJson());
            json.put("usuarios",usuarios.toJson());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try{
            carta.fromJson(json.getJSONObject("carta"));
            mesas.fromJson(json.getJSONObject("mesas"));
            pedidos.fromJson(json.getJSONObject("pedidos"));
            reservas.fromJson(json.getJSONObject("reservas"));
            usuarios.fromJson(json.getJSONObject("usuarios"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


}
