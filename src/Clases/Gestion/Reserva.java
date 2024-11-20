package Clases.Gestion;
import Clases.Usuarios.Cliente;
import Enums.EstadoReserva;

import java.util.Date;
import java.time.LocalTime;

public class Reserva {
    private final Date fecha;
    private final LocalTime hora;
    private final Mesa mesa;
    private final Cliente cliente;

    // Constructor
    public Reserva(Mesa mesa, Cliente cliente) {
        this.fecha = new Date();
        this.hora = LocalTime.now();
        this.mesa = mesa;
        this.cliente = cliente;
    }

    // Getters y setters
    public Date getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", mesa=" + mesa +
                '}';
    }

    
}
