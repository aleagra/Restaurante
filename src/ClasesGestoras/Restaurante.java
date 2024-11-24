package ClasesGestoras;

import Clases.Usuarios.Usuario;

public class Restaurante {
    Carta carta;
    Mesas mesas;
    Pedidos pedidos;
    Reservas reservas;
    Usuarios<Usuario> usuarios;

    public Restaurante() {
        this.carta = new Carta();
        this.mesas = new Mesas();
        this.pedidos = new Pedidos();
        this.reservas = new Reservas();
        this.usuarios = new Usuarios<>();
    }

    public Carta getCarta() {
        return carta;
    }

    public Mesas getMesas() {
        return mesas;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public Reservas getReservas() {
        return reservas;
    }

    public Usuarios<Usuario> getUsuarios() {
        return usuarios;
    }
}
