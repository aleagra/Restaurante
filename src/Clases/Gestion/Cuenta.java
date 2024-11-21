package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.MetodoPago;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Cuenta implements IJson {
    private static int contador = 1;
    public int idCuenta;
    public Cliente cliente;
    public double total;
    public MetodoPago metodoPago;
    public Pedido pedido;

    public Cuenta(Cliente cliente, double total, Pedido pedido) {
        this.idCuenta = contador++;
        this.cliente = cliente;
        this.total = total;
        this.metodoPago = MetodoPago.EFECTIVO;
        this.pedido = pedido;
    }

    public Cuenta() {
        this.idCuenta = contador++;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Cuenta.contador = contador;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "NUMERO DE PEDIDO: " + idCuenta +
                "\nNOMBRE: "+  cliente.getNombre()+
                "\nTOTAL: " + total +
                "\nMETODO DE PAGO: " + metodoPago +
                "\nPEDIDO: " + pedido.getEstado() +
                "\n\nBEBIDAS: "+ pedido.getNombresBebidas()+
                "\nPLATOS: "+ pedido.getNombresPlatos();
    }
    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("IdCuenta", idCuenta);
            obj.put("Cliente", cliente.toJson());
            obj.put("Total", total);
            obj.put("Metodo de Pago",metodoPago.name());
            obj.put("Pedido",pedido.toJson());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;

    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        try {
            this.idCuenta = json.getInt("IdCuenta");
            this.cliente.fromJson(json.getJSONObject("Cliente"));
            this.total = json.getDouble("Total");
            this.metodoPago = MetodoPago.valueOf(json.getString("Metodo de Pago"));
            this.pedido.fromJson(json.getJSONObject("Pedido"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double calcularTotal() {
        double total = 0;

        if (pedido != null) {
            if (pedido.getPlatos() != null) {
                for (Plato plato : pedido.getPlatos()) {
                    total += plato.getPrecio();
                }
            }

            if (pedido.getBebidas() != null) {
                for (Bebida bebida : pedido.getBebidas()) {
                    total += bebida.getPrecio();
                }
            }
        }

        return total;
    }
}