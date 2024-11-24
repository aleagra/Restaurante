package Clases.Gestion;
import Enums.TipoDePlato;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Plato implements IJson {
    private String nombre;
    private String descripcion;
    private double precio;
    private TipoDePlato categoria;


    public Plato(String nombre, String descripcion, double precio, TipoDePlato categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Plato() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoDePlato getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoDePlato categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return
                "NOMBRE: " + nombre + " " +
                "DESCRIPCION: " + descripcion + " " +
                "PRECIO: " + precio + " " +
                "CATEGORIA: " + categoria ;
    }
    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try{
            json.put("nombre",nombre);
            json.put("descripcion",descripcion);
            json.put("precio",precio);
            json.put("categoria",categoria);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            nombre = json.getString("nombre");
            descripcion = json.getString("descripcion");
            precio = json.getDouble("precio");
            categoria = TipoDePlato.valueOf(json.getString("categoria"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
