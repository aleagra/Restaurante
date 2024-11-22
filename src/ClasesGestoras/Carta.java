package ClasesGestoras;

import Clases.Gestion.Bebida;
import Clases.Gestion.Plato;
import Clases.Gestion.Reserva;
import Excepciones.BebidaException;
import Excepciones.PlatoException;
import Interfaces.IJson;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Carta {
    private List<Plato> comidas;
    private List<Bebida> bebidas;

    public Carta() {
        this.comidas = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public List<Plato> getComidas() {
        return comidas;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public Carta cargarCarta() {
        Carta carta = null;
        try {
            JSONObject jsonObject = new JSONObject(JSONUtiles.leerUnJson("carta.json"));

            carta = new Carta();

            JSONArray comidasArray = jsonObject.getJSONArray("comidas");
            for (int i = 0; i < comidasArray.length(); i++) {
                JSONObject comidaJson = comidasArray.getJSONObject(i);
                Plato plato = new Plato();
                plato.fromJson(comidaJson);
                carta.getComidas().add(plato);
            }

            JSONArray bebidasArray = jsonObject.getJSONArray("bebidas");
            for (int i = 0; i < bebidasArray.length(); i++) {
                JSONObject bebidaJson = bebidasArray.getJSONObject(i);
                Bebida bebida = new Bebida();
                bebida.fromJson(bebidaJson);
                carta.getBebidas().add(bebida);
            }

        } catch (JSONException e) {
            System.out.println("Error al leer el archivo JSON de la carta.");
            e.printStackTrace();
        }
        return carta;
    }

    public String agregarComida(Plato plato) {
        Carta carta = cargarCarta();
        if (plato != null) {
            if (plato.getPrecio() < 0) {
                return "⚠️ El precio del plato no puede ser negativo.";
            }

            boolean existe = carta.getComidas().stream()
                    .anyMatch(p -> p.getNombre().equalsIgnoreCase(plato.getNombre()));

            if (existe) {
                return "⚠️ El plato ya existe en la carta.";
            }

            carta.getComidas().add(plato);
            try {
                JSONUtiles.grabarUnJsonObject(carta.toJson(), "carta.json");
            } catch (JSONException e) {
                e.printStackTrace();
                return "⚠️ Error al guardar los cambios en carta.json.";
            }
            return "✅ Plato agregado correctamente.";
        } else {
            return "⚠️ El plato no es válido.";
        }
    }

    public String agregarBebida(Bebida bebida) {
        Carta carta = cargarCarta();
        if (bebida != null) {
            boolean existe = carta.getBebidas().stream()
                    .anyMatch(b -> b.getNombre().equalsIgnoreCase(bebida.getNombre()));

            if (existe) {
                return "⚠️ La bebida ya existe en la carta.";
            }

            carta.getBebidas().add(bebida);
            try {
                JSONUtiles.grabarUnJsonObject(carta.toJson(), "carta.json");
            } catch (JSONException e) {
                e.printStackTrace();
                return "⚠️ Error al guardar los cambios en carta.json.";
            }
            return "✅ Bebida agregada correctamente.";
        } else {
            return "⚠️ La bebida no es válida.";
        }
    }

    public String eliminarComida(String nombrePlato) {
        Carta carta = cargarCarta();
        for (Plato plato : carta.getComidas()) {
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                carta.getComidas().remove(plato);
                try {
                    JSONUtiles.grabarUnJsonObject(carta.toJson(), "carta.json");
                    return "✅ Plato eliminado correctamente.";
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "⚠️ Error al guardar los cambios en carta.json.";
                }
            }
        }
        return "⚠️ El plato no pudo ser eliminado. No existe en la carta.";
    }

    public String eliminarBebida(String nombreBebida) {
        Carta carta = cargarCarta();
        for (Bebida bebida : carta.getBebidas()) {
            if (bebida.getNombre().equalsIgnoreCase(nombreBebida)) {
                carta.getBebidas().remove(bebida);

                try {
                    JSONUtiles.grabarUnJsonObject(carta.toJson(), "carta.json");
                    return "✅ Bebida eliminada correctamente.";
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "⚠️ Error al guardar los cambios en carta.json.";
                }
            }
        }
        return "⚠️ La bebida no pudo ser eliminada. No existe en la carta.";
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

    public static Plato buscarComidaPorIdEnCarta(String rutaArchivoJson, int idComida) {
        try {
            JSONObject arregloCarta = new JSONObject(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloCarta.length() > 0) {
                JSONArray comidasArray = arregloCarta.getJSONArray("comidas");

                for (int i = 0; i < comidasArray.length(); i++) {
                    JSONObject comidaJson = comidasArray.getJSONObject(i);

                    if (comidaJson.getInt("id") == idComida) {
                        Plato comida = new Plato();
                        comida.fromJson(comidaJson);
                        return comida;
                    }
                }
            }
            return null;

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("⚠️ Error al procesar el JSON al buscar la comida con ID " + idComida);
            return null;
        }
    }



    public static Bebida buscarBebidaPorIdEnCarta(String rutaArchivoJson, int idBebida) {
        try {
            JSONObject arregloCarta = new JSONObject(JSONUtiles.leerUnJson(rutaArchivoJson));

            if (arregloCarta.length() > 0) {
                JSONArray bebidasArray = arregloCarta.getJSONArray("bebidas");

                for (int i = 0; i < bebidasArray.length(); i++) {
                    JSONObject bebidaJson = bebidasArray.getJSONObject(i);

                    if (bebidaJson.getInt("numero") == idBebida) {
                        Bebida bebida = new Bebida();
                        bebida.fromJson(bebidaJson);
                        return bebida;
                    }
                }
            }

            return null;

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("⚠️ Error al procesar el JSON al buscar la bebida con ID " + idBebida);
            return null;
        }
    }

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
