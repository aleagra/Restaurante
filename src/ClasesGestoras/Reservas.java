package ClasesGestoras;

import Clases.Gestion.Reserva;
import Excepciones.ReservasException;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public List<Reserva> cargarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(JSONUtiles.leerUnJson("reservas.json"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Reserva reserva = new Reserva();
                reserva.fromJson(jsonObject);

                reservas.add(reserva);
            }
        } catch (JSONException e) {
            System.out.println("Error al leer el archivo JSON de reservas.");
            e.printStackTrace();
        }
        return reservas;
    }

    public void agregarReserva(Reserva reserva) throws JSONException {
        if (reserva == null) {
            throw new ReservasException("⚠️ La reserva no puede ser nula.");
        }

        List<Reserva> reservas = cargarReservas();
        reservas.add(reserva);
        JSONArray jsonArray = new JSONArray();
        for (Reserva r : reservas) {
            jsonArray.put(r.toJson());
        }
        JSONUtiles.grabarUnJson(jsonArray,"reservas.json");
    }

    public String verReservas(){ // METODO TO JSON
        StringBuilder builder = new StringBuilder();
        for(Reserva reserva: reservas){
            builder.append(reserva.toString()).append("\n");
        }
        return builder.toString();
    }
}
