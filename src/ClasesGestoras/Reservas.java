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

    public static String mostrarReservasPorCliente(String email) {
        StringBuilder resultado = new StringBuilder();

        try {
            JSONArray arregloReservas = new JSONArray(JSONUtiles.leerUnJson("reservas.json"));

            if (arregloReservas.length() > 0) {
                resultado.append("\n----📅 TUS RESERVAS 📅----\n");

                boolean reservasEncontradas = false;

                for (int i = 0; i < arregloReservas.length(); i++) {
                    JSONObject jsonObject = arregloReservas.getJSONObject(i);

                    Reserva reserva = new Reserva();
                    reserva.fromJson(jsonObject);

                    if (reserva.getCliente().getEmail().equalsIgnoreCase(email)) {
                        resultado.append(detallesReserva(reserva)).append("\n");
                        reservasEncontradas = true;
                    }
                }

                if (!reservasEncontradas) {
                    resultado.append("⚠️ No se encontraron reservas para el cliente especificado.\n");
                }
            } else {
                resultado.append("⚠️ No se encontraron reservas en el archivo.\n");
            }
        } catch (JSONException e) {
            resultado.append("⚠️ No se ha podido leer el archivo.\n");
            e.printStackTrace();
        }

        return resultado.toString();
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

    public static String mostrarReservas() {
        StringBuilder resultado = new StringBuilder();

        try {
            JSONArray arregloReservas = new JSONArray(JSONUtiles.leerUnJson("reservas.json"));

            if (arregloReservas.length() > 0) {
                resultado.append("\n----📅 RESERVAS 📅----\n");

                for (int i = 0; i < arregloReservas.length(); i++) {
                    JSONObject jsonObject = arregloReservas.getJSONObject(i);

                    Reserva reserva = new Reserva();
                    reserva.fromJson(jsonObject);

                    resultado.append(detallesReserva(reserva)).append("\n");
                }
            } else {
                resultado.append("⚠️ No se encontraron reservas en el archivo.\n");
            }
        } catch (JSONException e) {
            resultado.append("⚠️ No se ha podido leer el archivo.\n");
            e.printStackTrace();
        }

        return resultado.toString();
    }

    private static String detallesReserva(Reserva reserva) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Fecha: ").append(reserva.getFecha())
                .append(" | Mesa Número: ").append(reserva.getMesa().getNumero())
                .append(" | Capacidad: ").append(reserva.getMesa().getCapacidad())
                .append(" | Cliente: ").append(reserva.getCliente().getNombre())
                .append(" ").append(reserva.getCliente().getApellido());
        return detalles.toString();
    }

}
