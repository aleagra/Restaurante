package Clases.Gestion;
import Enums.TipoDeBebida;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Bebida implements IJson {
    private String nombre;
    private double precio;
    private TipoDeBebida tipo;

    public Bebida(String nombre, double precio, TipoDeBebida tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Bebida() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoDeBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeBebida tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "NOMBRE:" + nombre +
                "PRECIO: " + precio +
                "TIPO: " + tipo;
    }
    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("nombre", nombre);
            obj.put("precio", precio);
            obj.put("tipo", tipo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            this.nombre = json.getString("nombre");
            this.precio = json.getDouble("precio");
            this.tipo = TipoDeBebida.valueOf(json.getString("tipo"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
