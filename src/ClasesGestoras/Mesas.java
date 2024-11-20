package ClasesGestoras;

import Clases.Gestion.Mesa;
import Enums.EstadoMesa;
import Excepciones.MesasException;
import Interfaces.IMetodosGestores;

import java.util.ArrayList;
import java.util.List;

public class Mesas implements IMetodosGestores<Mesa>{
    private ArrayList<Mesa> TodasLasMesas;

    public Mesas() {
        TodasLasMesas = new ArrayList<>();
    }

    /**Cuando se lo llama hay que pasarle por parametro mesas.TodasLasMesas*/
    @Override
    public String agregarElemento(List<Mesa> lista, Mesa elemento) throws Exception {
        return IMetodosGestores.super.agregarElemento(lista, elemento);
    }

    @Override
    public String eliminarElemento(List<Mesa> lista, Mesa elemento) throws Exception {
        return IMetodosGestores.super.eliminarElemento(lista, elemento);
    }

    @Override
    public String mostrarElementos(List<Mesa> lista) {
        return IMetodosGestores.super.mostrarElementos(lista);
    }

    public String mostrarMesasDisponibles(){
        StringBuilder builder = new StringBuilder();
        for (Mesa m : TodasLasMesas) {
            if(m.getEstadoMesa().equals(EstadoMesa.LIBRE)){
                builder.append(m);
            }
        }
        return builder.toString();
    }

    public Mesa asignarMesa(int capacidad) throws MesasException {
        Mesa mesa = null;
        for (Mesa m : TodasLasMesas) {
            if (m.capacidad == capacidad) {
                m.setEstadoMesa(EstadoMesa.OCUPADA);
                mesa = m;
                break;
            }
        }
        if (mesa == null) {
            throw new MesasException("No hay mesas con esa capacidad disponible");
        }
        return mesa;
    }



}
