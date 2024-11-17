package Interfaces;
import Clases.Gestion.Reserva;

public interface IGestorReserva {
    void agregarReserva(Reserva reserva);
    String obtenerReservas();
}
