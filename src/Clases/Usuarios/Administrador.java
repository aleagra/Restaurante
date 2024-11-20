package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Mesa;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import ClasesGestoras.Mesas;
import ClasesGestoras.Reservas;
import Excepciones.ContraseniaException;
import Interfaces.IGestorReserva;
import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Administrador extends Usuario implements IGestorReserva, IJson {

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

public boolean gestionarMenu(int opcion , Mesas mesas, Mesa m, Carta carta, Plato plato, Bebida bebida, String nombre) {
    switch (opcion) {
        case 1:
            carta.agregarComida(plato);
            break;
        case 2:
            carta.agregarBebida(bebida);
            break;
        case 3:
            carta.eliminarComida(nombre);
            break;
        case 4:
            carta.eliminarBebida(nombre);
            break;
        case 5:
            mesas.addMesa(m);
            break;
        case 6:
            mesas.deleteMesa(m);
            break;
        default:
            throw new IllegalArgumentException("Opción no válida para gestionar la carta");
    }
    return true;
}

    @Override
    public String cambiarContrasenia(String contraActual, String contraNueva) {
        String msj = "Contraseña cambiada.";
        if(contraActual.equals(this.contrasenia)){
            setContrasenia(contraNueva);
            return msj;
        }
        throw new ContraseniaException("La contraseña no se pudo cambiar.");
    }

    @Override
    public String obtenerReservas(Reservas res) {
        return res.verReservas();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        try {

            json.put("Nombre",this.nombre);
            json.put("Apellido",this.apellido);
            json.put("Email",this.email);
            json.put("Contrasenia",this.contrasenia);

        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
        return json;
    }

    @Override
    public void fromJson(JSONObject json)throws JSONException {

        try{
            this.nombre = json.getString("Nombre");
            this.apellido = json.getString("Apellido");
            this.email = json.getString("Email");
            this.contrasenia = json.getString("Contrasenia");
        }catch (JSONException e){
            throw new JSONException(e.getMessage());
        }
    }

}