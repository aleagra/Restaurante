package ClasesGestoras;

import Clases.Gestion.Bebida;
import Clases.Gestion.Plato;
import Excepciones.BebidaException;
import Excepciones.PlatoException;

import java.util.ArrayList;
import java.util.List;

public class Carta{
    private List<Plato> comidas;
    private List<Bebida> bebidas;

    public Carta() {
        this.comidas = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public String agregarComida(Plato plato){
        String msj = "Plato agregado exitiosamente.";
        if(!comidas.add(plato)){
            throw new PlatoException("El plato ya existe.");
        }
        return msj;
    }

    public String agregarBebida(Bebida bebida){
        String msj = "Bebida agregada exitosamente.";
        if(!bebidas.add(bebida)){
            throw new BebidaException("La bebida ya existe.");
        }
        return msj;
    }

    public String eliminarComida(String nombrePlato){
        for (Plato plato : comidas) {
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                comidas.remove(plato);
                return "Plato eliminado exitosamente.";
            }
        }
        throw new PlatoException("El plato no se encuentra.");
    }

    public String eliminarBebida(String nombreBebida) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNombre().equalsIgnoreCase(nombreBebida)) {
                bebidas.remove(bebida);
                return "Bebida eliminada exitosamente.";
            }
        }
        throw new BebidaException("La bebida no se encuentra.");
    }

    public String mostrarComidas(){
        StringBuilder msj= new StringBuilder();
        for(Plato p : comidas){
            msj.append(p).append("\n");
        }
        return msj.toString();
    }

    public String mostrarBebidas(){
        StringBuilder msj = new StringBuilder();
        for(Bebida b : bebidas){
            msj.append(b).append("\n");
        }
        return msj.toString();
    }
    public Plato buscarPlatoPorId(int idPlato) {
        for (Plato plato : comidas) {
            if (plato.getNumero() == idPlato) {
                return plato;
            }
        }
        throw new RuntimeException("El ID del plato es incorrecto o no existe");
    }

    public Bebida buscarBebidaPorId(int idBebida) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNumero() == idBebida) {
                return bebida;
            }
        }
        throw new RuntimeException("El ID de la bebida es incorrecto o no existe");
    }

}
