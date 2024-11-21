package Clases.Usuarios;


import ClasesGestoras.Pedidos;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorPedidos;
import Interfaces.IGestorReserva;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Cliente extends Usuario implements IGestorReserva, IGestorPedidos {
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, String email, String contrasenia, String telefono, String direccion) {
        super(nombre, apellido, email, contrasenia);
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Cliente(){
    }

    @Override
    public String toString() {
        return "CLIENTE: " + super.toString() +
                " TELEFONO: " + telefono + '\'' +
                " DIRECCION: "  + direccion;
    }

    @Override
    public String cambiarContrasenia(String contraActual, String contraNueva) {
        String msj = "✅ Contraseña cambiada.";
        if(contraActual.equals(this.contrasenia)){
            setContrasenia(contraNueva);
            return msj;
        }
        throw new ContraseniaException("⚠️ La contraseña no se pudo cambiar.");
    }

    @Override
    public String obtenerReservas(Reservas res) {
        return res.mostrarReservasPorCliente(email);
    }

    @Override
    public String obtenerPedidos(Pedidos pedidos) {
        return pedidos.mostrarPedidosPorCliente(email);
    }


}