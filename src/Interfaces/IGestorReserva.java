package Interfaces;
import Clases.Reserva;
import java.util.List;

public interface IGestorReserva {
    void agregarReserva(Reserva reserva);
    String obtenerReservas();
}
