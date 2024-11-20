package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.EstadoPedido;
import Excepciones.BebidaException;
import Excepciones.PlatoException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements IJson {
    private int numeroPedido;
    private static int contador = 1;
    private List<Plato> platos;
    private List<Bebida> bebidas;
    private EstadoPedido estado;
    private Cliente cliente;

    public Pedido(EstadoPedido estado, Cliente cliente) {
        this.numeroPedido = contador++;
        this.platos = new ArrayList<>();
        this.bebidas = new ArrayList<>();
        this.estado = estado;
        this.cliente = cliente;
    }
    public  Pedido(){
        this.numeroPedido = contador++;
    }
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void actualizarEstadoPedido(){
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", platos=" + platos +
                ", bebidas=" + bebidas +
                ", estado=" + estado +
                ", cliente=" + cliente +
                '}';
    }
    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("numeroPedido", numeroPedido);

            JSONArray platosArray = new JSONArray();
            for (Plato plato : platos) {
                platosArray.put(plato.toJson());
            }
            obj.put("platos", platosArray);

            JSONArray bebidasArray = new JSONArray();
            for (Bebida bebida : bebidas) {
                bebidasArray.put(bebida.toJson());
            }
            obj.put("bebidas", bebidasArray);

            obj.put("estado", estado);


            obj.put("cliente", cliente.toJson());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            numeroPedido = json.getInt("numeroPedido");

            // Deserializar platos desde JSONArray
            platos = new ArrayList<>();
            JSONArray platosArray = json.getJSONArray("platos");
            for (int i = 0; i < platosArray.length(); i++) {
                Plato pl = new Plato();
                pl.fromJson(platosArray.getJSONObject(i));
                platos.add(pl);
            }


            bebidas = new ArrayList<>();
            JSONArray bebidasArray = json.getJSONArray("bebidas");
            for (int i = 0; i < bebidasArray.length(); i++) {
                Bebida bebida = new Bebida();
                bebida.fromJson(bebidasArray.getJSONObject(i));
                bebidas.add(bebida);
            }

            estado = EstadoPedido.valueOf(json.getString("estado"));


            cliente = new Cliente();
            cliente.fromJson(json.getJSONObject("cliente"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean addBebida(Bebida bebida) throws BebidaException {
        if(bebida!=null){
            this.bebidas.add(bebida);
            return true;
        }
        throw new BebidaException("La bebida no existe");
    }
    public boolean addPlato(Plato pl) throws PlatoException {
        if(pl!=null){
            this.platos.add(pl);
            return true;
        }
        throw new PlatoException("El plato no existe");
    }
    public int actualizarEstadoPedido(EstadoPedido nuevoEstado) {
        int flag = 0;
        if (nuevoEstado != null) {
            this.estado = nuevoEstado;
            flag = 1;
        }
        return flag;
    }

    public List<String> getNombresPlatos() {
        List<String> nombresPlatos = new ArrayList<>();
        for (Plato plato : platos) {
            nombresPlatos.add(plato.getNombre());
        }
        return nombresPlatos;
    }

    public List<String> getNombresBebidas() {
        List<String> nombresBebidas = new ArrayList<>();
        for (Bebida bebida : bebidas) {
            nombresBebidas.add(bebida.getNombre());
        }
        return nombresBebidas;
    }

}