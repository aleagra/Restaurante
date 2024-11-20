package Clases.Gestion;
import Clases.Usuarios.Cliente;
import Enums.EstadoReserva;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalTime;

public class Reserva implements IJson {
    private  Date fecha;
    private  LocalTime hora;
    private  Mesa mesa;
    private  Cliente cliente;

    // Constructor
    public Reserva(Mesa mesa, Cliente cliente) {
        this.fecha = new Date();
        this.hora = LocalTime.now();
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public Reserva() {
    }

    // Getters y setters
    public Date getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
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
                ", hora=" + hora +
                ", mesa=" + mesa +
                '}';
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("fecha", fecha.toString());
            obj.put("hora", hora);
            obj.put("mesa", mesa);
            obj.put("cliente", cliente.toJson());
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {

        try {
            String fechaString = json.getString("fecha");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(dateFormat.parse(fechaString).getTime());

            String horaString = json.getString("hora");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.hora = LocalTime.parse(horaString, formatter);

            mesa.fromJson( json.getJSONObject("mesa"));
            cliente.fromJson( json.getJSONObject("cliente"));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }catch (JSONException a){
            a.printStackTrace();
        }
    }

}
