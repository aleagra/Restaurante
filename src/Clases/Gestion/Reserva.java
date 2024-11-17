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
    private EstadoReserva estado;

    // Constructor
    public Reserva(Mesa mesa, Cliente cliente) {
        this.fecha = new Date();
        this.hora = LocalTime.now();
        this.mesa = mesa;
        this.cliente = cliente;
        this.estado = EstadoReserva.CONFIRMADA;
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

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", mesa=" + mesa +
                ", cliente=" + cliente +
                ", estado='" + estado + '\'' +
                '}';
    }

    public void cancelarReserva(){
        this.estado = EstadoReserva.CANCELADA;
    }
    
}
