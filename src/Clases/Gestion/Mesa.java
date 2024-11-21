package Clases.Gestion;

import Enums.EstadoMesa;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Mesa implements IJson {
    private static int contador=1;
    public int numero;
    public int capacidad;
    public EstadoMesa estadoMesa;

    public Mesa(int capacidad) {
        this.numero = contador++;
        this.capacidad = capacidad;
        this.estadoMesa = EstadoMesa.LIBRE;
    }
    public Mesa(){
        this.numero = contador++;
        this.estadoMesa = EstadoMesa.LIBRE;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public EstadoMesa getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "numero=" + numero +
                ", capacidad=" + capacidad +
                ", estadoMesa=" + estadoMesa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mesa mesa)) return false;
        return estadoMesa == mesa.estadoMesa;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(estadoMesa);
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try{
            json.put("numero", numero);
            json.put("capacidad", capacidad);
            json.put("estadoMesa", estadoMesa);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try{
            numero = json.getInt("numero");
            capacidad = json.getInt("capacidad");
            estadoMesa = EstadoMesa.valueOf(json.getString("estadoMesa"));

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}