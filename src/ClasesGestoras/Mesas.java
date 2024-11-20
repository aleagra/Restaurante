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
    private ArrayList<Mesa> TodasLasMesas;

    public Mesas() {
        TodasLasMesas = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try{
            JSONArray mesasJson = new JSONArray();
            for(Mesa m : TodasLasMesas){
                mesasJson.put(m.toJson());
            }
            obj.put("mesas", mesasJson);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            JSONArray mesasArray = json.getJSONArray("mesas");
            for(int i = 0; i < mesasArray.length(); i++){
                Mesa mesa = new Mesa();
                mesa.fromJson(mesasArray.getJSONObject(i));
                TodasLasMesas.add(mesa);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public String addMesa(Mesa m) throws MesasException {
        String msj = "La mesa se aniadio con exito!";
        if(m!= null){
            TodasLasMesas.add(m);
        }else{
            throw new MesasException("El mesa no puede ser null");
        }

        return msj;
    }

    public String deleteMesa(Mesa m) throws MesasException {
        String msj = "La mesa se eliminado con exito!";
            if(TodasLasMesas.contains(m)){
                TodasLasMesas.remove(m);
            }else {
                throw new MesasException("El mesa no existe");
            }
            return msj;
    }

    public String mostrarMesas(){
        StringBuilder builder = new StringBuilder();
        for (Mesa m : TodasLasMesas) {
            builder.append(m.toString());
        }
        return builder.toString();
    }

    public String mostrarMesasDisponibles(){
        StringBuilder builder = new StringBuilder();
        for (Mesa m : TodasLasMesas) {
            if(m.getEstadoMesa().equals(EstadoMesa.LIBRE)){
                builder.append(m);
            }
        }
        return builder.toString();
    }

    public Mesa asignarMesa(int capacidad) throws MesasException {
        Mesa mesa = null;
        for (Mesa m : TodasLasMesas) {
            if (m.capacidad == capacidad) {
                m.setEstadoMesa(EstadoMesa.OCUPADA);
                mesa = m;
                break;
            }
        }
        if (mesa == null) {
            throw new MesasException("No hay mesas con esa capacidad disponible");
        }
        return mesa;
    }



}
