package ClasesGestoras;

import Clases.Gestion.Mesa;
import Enums.EstadoMesa;
import Excepciones.MesasException;
import Interfaces.IJson;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.text.Utilities;
import java.util.ArrayList;

public class Mesas implements IJson {
    private ArrayList<Mesa> mesas;

    public Mesas() {
        mesas = new ArrayList<>();
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public String addMesa(Mesa m) throws MesasException, JSONException {
        String msj = "✅ La mesa se añadió con éxito!";
        if(m!= null){
            mesas.add(m);
        }else{
            throw new MesasException("⚠️ La mesa no puede ser null");
        }

        return msj;
    }

    public String deleteMesa(int nroMesa, String fileName) throws MesasException {
        String msj = "";

        try {
            JSONArray jsonContent = new JSONArray(JSONUtiles.leerUnJson(fileName));

            for (int i = 0; i < jsonContent.length(); i++) {
                JSONObject jsonObject = jsonContent.getJSONObject(i);


                if (jsonObject.getInt("numero") == nroMesa) {
                    jsonContent.remove(i);
                    JSONUtiles.grabarUnJson(jsonContent, fileName);
                    msj = "✅ Mesa con ID " + nroMesa + " eliminada correctamente.";
                    return msj;
                }
            }

            msj = "⚠️ La mesa con ID " + nroMesa + " no existe.";
        } catch (JSONException e) {
            throw new MesasException("⚠️ Error al procesar el JSON.");
        }

        if (msj.isEmpty()) {
            throw new MesasException("⚠️ La mesa no se pudo eliminar.");
        }
        return msj;
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

    @Override
    public JSONObject toJson() throws JSONException {
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
    public void fromJson(JSONObject json) throws JSONException {
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
