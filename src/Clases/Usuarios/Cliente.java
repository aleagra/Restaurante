package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Pedido;
import Clases.Gestion.Plato;
import Clases.Gestion.Reserva;
import ClasesGestoras.Pedidos;
import Enums.EstadoReserva;
import Interfaces.IGestorReserva;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario implements IGestorReserva {
    private String telefono;
    private String direccion;
    private List<Reserva> historialReservas;
    Pedidos pedidos;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String telefono, String direccion) {
        super(nombre, apellido, email, contrasenia);
        this.telefono = telefono;
        this.direccion = direccion;
        this.historialReservas = new ArrayList<>();
        this.pedidos = new Pedidos();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    public void setHistorialReservas(List<Reserva> historialReservas) {
        this.historialReservas = historialReservas;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString()+
                "telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", historialReservas=" + historialReservas +
                '}';
    }

    @Override
    public boolean cambiarContrasenia() {
        return false;
    }


    public String hacerPedido(Pedido pedido){
        /*recibe un plato y una bebida */
        return pedidos.agregarPedido(pedido);
    }


    public String consultarMenu(){
        return null;
    }

    public boolean cancelarReserva(Reserva reserva) {
        if (historialReservas.contains(reserva)) {
            reserva.setEstado(EstadoReserva.CANCELADA);
            return true;
        }
        return false;
    }

    @Override
    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    @Override
    public String obtenerReservas() {
        StringBuilder reservas = new StringBuilder();
        for (Reserva reserva : historialReservas){
            reservas.append(reserva.toString()).append("\n");
        }
        return reservas.toString();
    }
}