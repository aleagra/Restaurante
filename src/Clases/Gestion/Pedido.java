package Clases.Gestion;

import Clases.Usuarios.Cliente;
import Enums.EstadoPedido;
import Excepciones.BebidaException;
import Excepciones.PlatoException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
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