package ClasesGestoras;

import Clases.Gestion.Reserva;
import Excepciones.ReservasException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reservas {
    private ArrayList<Reserva> reservas;
    private static int contador = 1;
    public int nroReserva;

    public Reservas() {
        this.reservas = new ArrayList<>();
        this.nroReserva = contador++;
    }


    public  JSONArray toJson() throws JSONException {
        JSONArray reservasJson = new JSONArray();
        try {
            for (Reserva m : reservas) {
                reservasJson.put(m.toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reservasJson;
    }

    public void fromJson(JSONObject json) throws JSONException {
        try {
            JSONArray reservasArray = json.getJSONArray("reservas");
            for(int i = 0; i < reservasArray.length(); i++){
                Reserva rer = new Reserva();
                rer.fromJson(reservasArray.getJSONObject(i));
                reservas.add(rer);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String mostrarReservasPorCliente(String email){
        StringBuilder lista = new StringBuilder();
        for(Reserva reserva : reservas){
            if(reserva.getCliente().getEmail().equals(email)){
                lista.append(reserva).append("\n");
            }
        }
        return lista.toString();
    }

    public void aniadirReserva (Reserva reserva) throws ReservasException { //METODO TOJSON
        if(reserva!=null){
            this.reservas.add(reserva);
        }else{
            throw new ReservasException("⚠️ La reserva no puede ser nula");
        }
    }

    public String verReservas(){ // METODO TO JSON
        StringBuilder builder = new StringBuilder();
        for(Reserva reserva: reservas){
            builder.append(reserva.toString()).append("\n");
        }
        return builder.toString();
    }
}
