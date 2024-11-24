package ClasesGestoras;
import Clases.Gestion.Mesa;
import Enums.EstadoMesa;
import Excepciones.MesasException;
import Interfaces.IJson;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Mesas implements IJson {
    private ArrayList<Mesa> mesas;

    public Mesas() {
        mesas = new ArrayList<>();
    }

    public List<Mesa> cargarMesas() {
        List<Mesa> mesas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(JSONUtiles.leerUnJson("mesas.json"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Mesa mesa = new Mesa();
                mesa.fromJson(jsonObject);

                mesas.add(mesa);
            }
        } catch (JSONException e) {
            System.out.println("Error al leer el archivo JSON de mesas.");
            e.printStackTrace();
        }
        return mesas;
    }

    public String addMesa(Mesa mesa) throws JSONException {
        if (mesa == null) {
            throw new MesasException("⚠️ La mesa no puede ser nula.");
        }
        List<Mesa> mesas = cargarMesas();

        int nuevoId = mesas.stream()
                .mapToInt(Mesa::getNumero)
                .max()
                .orElse(0) + 1;
        mesa.setNumero(nuevoId);

        mesas.add(mesa);

        JSONArray jsonArray = new JSONArray();
        for (Mesa m : mesas) {
            jsonArray.put(m.toJson());
        }
        JSONUtiles.grabarUnJson(jsonArray, "mesas.json");
        return "Mesa agregada con éxito. ID asignado: " + nuevoId;
    }


    public String deleteMesa(int numeroMesa) {
        try {
            List<Mesa> mesas = cargarMesas();

            boolean eliminado = mesas.removeIf(m -> m.getNumero() == numeroMesa);

            if (!eliminado) {
                throw new MesasException("⚠️ No se encontró una mesa con el número: " + numeroMesa);
            }

            JSONArray jsonArray = new JSONArray();
            for (Mesa m : mesas) {
                jsonArray.put(m.toJson());
            }

            JSONUtiles.grabarUnJson(jsonArray, "mesas.json");

            return "✅ Mesa con número " + numeroMesa + " eliminada con éxito.";

        } catch (MesasException e) {
            return e.getMessage();
        } catch (JSONException e) {
            return "⚠️ Error al procesar los datos de las mesas.";
        }
    }

    public Mesa asignarMesa(int capacidad) throws MesasException {
        Mesa mesa = null;

        try {
            JSONArray arregloMesas = new JSONArray(JSONUtiles.leerUnJson("mesas.json"));

            for (int i = 0; i < arregloMesas.length(); i++) {
                JSONObject jsonObject = arregloMesas.getJSONObject(i);

                Mesa m = new Mesa(0);
                m.fromJson(jsonObject);

                if (m.getCapacidad() == capacidad && m.getEstadoMesa() == EstadoMesa.LIBRE) {
                    m.setEstadoMesa(EstadoMesa.OCUPADA);
                    JSONObject mesaJsonModificada = m.toJson();
                    arregloMesas.put(i, mesaJsonModificada);
                    mesa = m;
                    break;
                }
            }

            if (mesa == null) {
                throw new MesasException("⚠️ No hay mesas con esa capacidad disponible");
            }

            JSONUtiles.grabarUnJson(arregloMesas, "mesas.json");

        } catch (JSONException e) {
            e.printStackTrace();
            throw new MesasException("⚠️ Error al procesar el archivo de mesas.");
        }

        return mesa;
    }

    public static String mostrarTodasLasMesas(String rutaArchivoJson) {
        StringBuilder resultado = new StringBuilder();

        try {
            JSONArray arregloMesas = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloMesas.length() > 0) {
                for (int i = 0; i < arregloMesas.length(); i++) {
                    JSONObject jsonObject = arregloMesas.getJSONObject(i);

                    Mesa mesa = new Mesa();
                    mesa.fromJson(jsonObject);

                    resultado.append(detallesMesa(mesa)).append("\n");
                }
            } else {
                resultado.append("⚠️ No se encontraron mesas en el archivo.\n");
            }
        } catch (JSONException e) {
            resultado.append("⚠️ No se ha podido leer el archivo.\n");
            e.printStackTrace();
        }

        return resultado.toString();
    }

    private static String detallesMesa(Mesa mesa) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("NUMERO: ").append(mesa.getNumero())
                .append(" | CAPACIDAD: ").append(mesa.getCapacidad())
                .append(" | ESTADO: ").append(mesa.getEstadoMesa());
        return detalles.toString();
    }

    public static String mostrarMesasDisponibles(String rutaArchivoJson) {
        StringBuilder resultado = new StringBuilder();

        try {
            JSONArray arregloMesas = new JSONArray(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloMesas.length() > 0) {
                resultado.append("\n--- Mesas Disponibles ---\n");

                for (int i = 0; i < arregloMesas.length(); i++) {
                    JSONObject jsonObject = arregloMesas.getJSONObject(i);

                    Mesa mesa = new Mesa();
                    mesa.fromJson(jsonObject);

                    if (mesa.getEstadoMesa().equals(EstadoMesa.LIBRE)) {
                        resultado.append(detallesMesa(mesa)).append("\n");
                    }
                }
            } else {
                resultado.append("⚠️ No se encontraron mesas en el archivo.\n");
            }
        } catch (JSONException e) {
            resultado.append("⚠️ No se ha podido leer el archivo.\n");
            e.printStackTrace();
        }

        return resultado.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        try {
            JSONArray mesasJson = new JSONArray();
            for (Mesa mesa : mesas) {
                mesasJson.put(mesa.toJson());
            }
            obj.put("mesas", mesasJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json){
        try {
            JSONArray mesasArray = json.getJSONArray("mesas");
            mesas.clear();
            for (int i = 0; i < mesasArray.length(); i++) {
                Mesa mesa = new Mesa();
                mesa.fromJson(mesasArray.getJSONObject(i));
                mesas.add(mesa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
