package ClasesGestoras;

import Clases.Gestion.Mesa;
import Enums.EstadoMesa;
import Excepciones.MesasException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mesas implements IJson {
    private ArrayList<Mesa> mesas;

    public Mesas() {
        mesas = new ArrayList<>();
    }

    public String addMesa(Mesa m) throws MesasException {
        String msj = "✅ La mesa se añadió con éxito!";
        if(m!= null){
            mesas.add(m);
        }else{
            throw new MesasException("⚠️ La mesa no puede ser null");
        }

        return msj;
    }

    public String deleteMesa(int nroMesa) throws MesasException {
        String msj = "";
        for(Mesa m : mesas){
            if(m.getNumero() == nroMesa){
                mesas.remove(m);
                msj = "✅ La mesa se eliminó con éxito.";
            }
        }
        if(msj.isEmpty()){
            throw new MesasException("⚠️ La mesa no se pudo eliminar.");
        }
        return msj;
    }

    public String mostrarMesas(){
        StringBuilder builder = new StringBuilder();
        for (Mesa m : mesas) {
            builder.append(m.toString());
        }
        return builder.toString();
    }

    public String mostrarMesasDisponibles(){
        StringBuilder builder = new StringBuilder();
        for (Mesa m : mesas) {
            if(m.getEstadoMesa().equals(EstadoMesa.LIBRE)){
                builder.append(m);
            }
        }
        return builder.toString();
    }

    public Mesa asignarMesa(int capacidad) throws MesasException {
        Mesa mesa = null;
        for (Mesa m : mesas) {
            if (m.capacidad == capacidad) {
                m.setEstadoMesa(EstadoMesa.OCUPADA);
                mesa = m;
                break;
            }
        }
        if (mesa == null) {
            throw new MesasException("⚠️ No hay mesas con esa capacidad disponible");
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
