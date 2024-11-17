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

    public String eliminarComida(Plato plato){
        String msj = "Comida eliminada exitosamente.";
        if(!comidas.contains(plato)){
            throw new PlatoException("La comida no se encuentra.");
        }
        return msj;
    }

    public String eliminarBebida(Bebida bebida){
        String msj = "Bebida eliminada exitosamente.";
        if(!bebidas.contains(bebida)){
            throw new BebidaException("La bebida no se encuentra.");
        }
        return msj;
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
}
