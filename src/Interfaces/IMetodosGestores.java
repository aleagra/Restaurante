package Interfaces;

import Clases.Gestion.Mesa;

import java.util.List;

public interface IMetodosGestores<T> {


    default String agregarElemento(List<T> lista, T elemento) throws Exception {
        if (elemento != null) {
            lista.add(elemento);
            return "Elemento agregado con exito!";
        }
        throw new Exception("El elemento no pudo ser agregado");
    }
    default String eliminarElemento(List<T> lista, T elemento) throws Exception {
        if (elemento != null) {
            lista.remove(elemento);
            return "Elemento eliminado con exito!";
        }
        throw new Exception("El elemento no pudo ser eliminado");
    }
    default String mostrarElementos(List<T> lista) {
        StringBuilder sb = new StringBuilder();
        for (T elemento : lista) {
            sb.append(elemento).append("\n");
        }
        return sb.toString();
    }

}