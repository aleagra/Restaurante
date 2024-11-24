package ClasesGestoras;
import Clases.Gestion.Pedido;
import Excepciones.PedidoException;
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
        try {
            if (pedido != null) {
                pedidos.add(pedido);
                msj = "✅ El pedido fue creado correctamente con el número de orden " + pedido.getNumeroPedido();
            } else {
                throw new PedidoException("⚠️ El pedido no pudo ser agregado");
            }
        } catch (PedidoException e) {
            System.out.println(e.getMessage());
            msj = "⚠️ No se pudo agregar el pedido.";
        }
        return msj;
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
            if(pedido.getCliente().getEmail().equalsIgnoreCase(email)) {
                lista.append(pedido).append("\n");
            }
        }
        if (lista.isEmpty()){
            lista.append("⚠️ No se encontraron pedidos para el cliente especificado.");
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