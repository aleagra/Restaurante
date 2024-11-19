package ClasesGestoras;

import Clases.Gestion.Reserva;
import Excepciones.ReservasException;
import Interfaces.IMetodosGestores;

import java.util.ArrayList;
import java.util.List;

public class Reservas implements IMetodosGestores<Reserva> {
    private ArrayList<Reserva> reservas;
    private static int contador = 1;
    public int nroReserva;

    public Reservas() {
        this.reservas = new ArrayList<>();
        this.nroReserva = contador++;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Reservas.contador = contador;
    }

    public int getNroReserva() {
        return nroReserva;
    }

    public void setNroReserva(int nroReserva) {
        this.nroReserva = nroReserva;
    }

    /**Hay que pasarle por parametro Reserva.reservas*/
    @Override
    public String agregarElemento(List<Reserva> lista, Reserva elemento) throws Exception {
        return IMetodosGestores.super.agregarElemento(lista, elemento);
    }

    @Override
    public String eliminarElemento(List<Reserva> lista, Reserva elemento) throws Exception {
        return IMetodosGestores.super.eliminarElemento(lista, elemento);
    }

    @Override
    public String mostrarElementos(List<Reserva> lista) {
        return IMetodosGestores.super.mostrarElementos(lista);
    }

    public String mostrarReservasPorCliente(String email){
        StringBuilder lista = new StringBuilder();
        for(Reserva reserva : reservas){
            if(reserva.getCliente().getDireccion().equalsIgnoreCase(email)){
                lista.append(reserva).append("\n");
            }
        }
        return lista.toString();
    }
}
