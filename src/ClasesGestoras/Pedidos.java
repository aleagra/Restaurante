package ClasesGestoras;

import Clases.Gestion.Pedido;
import Excepciones.PedidoException;
import Interfaces.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private List<Pedido> pedidos;

    public Pedidos() {
        this.pedidos = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String agregarPedido(Pedido pedido) throws PedidoException {
        String msj = "";
        if (pedido != null) {
            pedidos.add(pedido);
            msj=("✅ El pedido fue creado correctamente con el numero de orden" + " " + pedido.getNumeroPedido());
            return msj;
        }
        throw new PedidoException("⚠️ El pedido no pudo ser agregado");
    }

    public String mostrarPedidos(){
        StringBuilder sb = new StringBuilder();
        for(Pedido p : pedidos){
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
    public String mostrarPedidosPorCliente(String email){
        StringBuilder lista = new StringBuilder();
        for(Pedido pedido : pedidos){
            if(pedido.getCliente().getEmail().equalsIgnoreCase(email)){
                lista.append(pedido).append("\n");
            }
        }
        return lista.toString();
    }
    public String eliminarPedido(Pedido pedido) throws PedidoException{
        String msj = "✅ Pedido eliminado exitosamente.";
        if(pedido != null){
            pedidos.remove(pedido);
            return msj;
        }
        throw new PedidoException("⚠️ El pedido no pudo ser eliminado");
    }
}