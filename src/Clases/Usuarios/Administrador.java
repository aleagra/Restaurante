package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorReserva;
import JSONUtiles.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;

public class Administrador extends Usuario implements IGestorReserva {

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    private void actualizarJson(String fileName, Object data) {
        try {
            JSONArray jsonArray = new JSONArray();
            if (data instanceof Carta) {
                jsonArray.put(((Carta) data).toJson());
            } else if (data instanceof Mesas) {
                for (Mesa mesa : ((Mesas) data).getMesas()) {
                    jsonArray.put(mesa.toJson());
                }
            }
            JSONUtiles.grabarUnJson(jsonArray, fileName);
        } catch (JSONException e) {
            System.out.println("Error al actualizar el archivo JSON: " + fileName);
            e.printStackTrace();
        }
    }

    public void gestionarMenu(int opcion , Mesas mesas, Mesa m, int nroMesa, Carta carta, Plato plato, Bebida bebida, String nombre) {
        switch (opcion) {
        case 1:
            System.out.println(carta.agregarComida(plato));
            actualizarJson("carta.json", carta);
            break;
        case 2:
            System.out.println(carta.agregarBebida(bebida));
            actualizarJson("carta.json", carta);
            break;
        case 3:
            System.out.println(carta.eliminarComida(nombre));
            actualizarJson("carta.json", carta);
            break;
        case 4:
            System.out.println(carta.eliminarBebida(nombre));
            actualizarJson("carta.json", carta);
            break;
        case 5:
            System.out.println(mesas.addMesa(m));
            actualizarJson("mesas.json", mesas);
            break;
        case 6:
            System.out.println(mesas.deleteMesa(nroMesa));
            actualizarJson("mesas.json", mesas);
            break;
        default:
            throw new IllegalArgumentException("Opción no válida para gestionar la carta");
    }
}
    @Override
    public String toString() {
        return "ADMINISTRADOR: " +
                " ID: " + id +
                " NOMBRE: " + nombre + '\'' +
                " APELLIDO: " + apellido + '\'' +
                " EMAIL: " + email;
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
    public String obtenerReservas(Reservas res) { // CAMBIAR A JSON
        return res.verReservas();
    }



}