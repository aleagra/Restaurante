package ClasesGestoras;

import Clases.Gestion.Reserva;
import Excepciones.ReservasException;

import java.util.ArrayList;

public class Reservas {
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


    public String mostrarReservasPorCliente(String email){
        StringBuilder lista = new StringBuilder();
        for(Reserva reserva : reservas){
            if(reserva.getCliente().getDireccion().equalsIgnoreCase(email)){
                lista.append(reserva).append("\n");
            }
        }
        return lista.toString();
    }


    public void aniadirReserva (Reserva reserva) throws ReservasException {
        if(reserva!=null){
            this.reservas.add(reserva);
        }else{
            throw new ReservasException("La reserva no puede ser nula");
        }
    }
    public void eliminarReserva(int nroReserva) throws ReservasException {
        if(nroReserva>0){
            reservas.remove(nroReserva);
        }else{
            throw new ReservasException("La reserva no existe");
        }
    }
    public String verReservas(){
        StringBuilder builder = new StringBuilder();
        for(Reserva reserva: reservas){
            builder.append(reserva.toString());
        }
        return builder.toString();
    }
}
