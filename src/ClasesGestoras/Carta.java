package ClasesGestoras;

import Clases.Gestion.Bebida;
import Clases.Gestion.Plato;
import Excepciones.BebidaException;
import Excepciones.PlatoException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Carta implements IJson {
    private List<Plato> comidas;
    private List<Bebida> bebidas;

    public Carta() {
        this.comidas = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public String agregarComida(Plato plato){
        String msj = "✅ Plato agregado exitiosamente.";
        if(!comidas.add(plato)){
            throw new PlatoException("⚠️ El plato ya existe.");
        }
        return msj;
    }

    public String agregarBebida(Bebida bebida){
        String msj = "✅ Bebida agregada exitosamente.";
        if(!bebidas.add(bebida)){
            throw new BebidaException("⚠️ La bebida ya existe.");
        }
        return msj;
    }

    public String eliminarComida(String nombrePlato){
        for (Plato plato : comidas) {
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                comidas.remove(plato);
                return "✅ Plato eliminado correctamente.";
            }
        }
        throw new PlatoException("⚠️ El plato no pudo ser eliminado.");
    }

    public String eliminarBebida(String nombreBebida) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNombre().equalsIgnoreCase(nombreBebida)) {
                bebidas.remove(bebida);
                return "✅ Bebida eliminada correctamente.";
            }
        }
        throw new BebidaException("⚠️ La bebida no pudo ser eliminada.");
    }

    public String mostrarComidas(){
        StringBuilder msj= new StringBuilder();
        for(Plato p : comidas){
            msj.append(p).append("\n");
        }
        return msj.toString();
    }

    public String mostrarBebidas(){
        StringBuilder msj = new StringBuilder();
        for(Bebida b : bebidas){
            msj.append(b).append("\n");
        }
        return msj.toString();
    }
    public Plato buscarPlatoPorId(int idPlato) {
        for (Plato plato : comidas) {
            if (plato.getNumero() == idPlato) {
                return plato;
            }
        }
        throw new RuntimeException("⚠️ El ID del plato es incorrecto o no existe");
    }

    public Bebida buscarBebidaPorId(int idBebida) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNumero() == idBebida) {
                return bebida;
            }
        }
        throw new RuntimeException("⚠️ El ID de la bebida es incorrecto o no existe");
    }
    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try {

            JSONArray comidasJsonArray = new JSONArray();
            for (Plato comida : comidas) {
                comidasJsonArray.put(comida.toJson());
            }


            JSONArray bebidasJsonArray = new JSONArray();
            for (Bebida bebida : bebidas) {
                bebidasJsonArray.put(bebida.toJson());
            }


            json.put("comidas", comidasJsonArray);
            json.put("bebidas", bebidasJsonArray);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            JSONArray comidasArray = json.getJSONArray("comidas");
            for (int i = 0; i < comidasArray.length(); i++) {
                JSONObject comidaJson = comidasArray.getJSONObject(i);
                Plato comida = new Plato();
                comida.fromJson(comidaJson);
                comidas.add(comida);
            }
            JSONArray bebidasArray = json.getJSONArray("bebidas");
            for (int i = 0; i < bebidasArray.length(); i++) {
                JSONObject bebidaJson = bebidasArray.getJSONObject(i);
                Bebida bebida = new Bebida();
                bebida.fromJson(bebidaJson);
                bebidas.add(bebida);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
