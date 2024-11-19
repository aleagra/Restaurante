package Menus;

import Clases.Gestion.Pedido;
import Clases.Usuarios.Cocinero;
import ClasesGestoras.Pedidos;
import Enums.EstadoPedido;

import java.util.Scanner;

public class MenuCocinero {

    public void mostrarMenuCocinero(Pedidos pedidos, Scanner sc){
        int opcion;
        char seguir = 's';
        Cocinero cocinera = new Cocinero("Sol","Giuria","solgiuria@gmail.com","aaa111");

        do{
            System.out.println("-----------------MENU---------------");
            opciones();
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    System.out.println("Mostrando los pedidos...");
                    cocinera.verPedido(pedidos);
                    break;
                case 2:
                    System.out.println("Actualizando el estado del pedido...");
                    int nroPedido = sc.nextInt();
                    cocinera.actualizarEstadoPedido(pedidos,nroPedido,EstadoPedido.LISTO);
                    break;
                case 3:
                    System.out.println("Cambiando contrasenia...");
                    System.out.println("Ingrese la contrasenia actual: ");
                    String contraActual = sc.nextLine();
                    System.out.println("Ingrese la contrasenia nueva: ");
                    String contraNueva = sc.nextLine();
                    cocinera.cambiarContrasenia(contraActual, contraNueva);
                    break;

                default: {
                    System.out.println("Número fuera de rango. Intenta de nuevo.");
                    // No se usa break aca porque queremos que el do-while vuelva a ejecutarse.
                    continue;
                }
            }
            System.out.println("Desea continuar? s/n: ");
            seguir = sc.next().charAt(0);

        }while(seguir=='s');
    }


    public static void opciones(){
        System.out.println("1-Ver pedidos");
        System.out.println("2-Actualizar estado del pedido");
        System.out.println("3- Cambiar contrasenia");
        System.out.println("4- Salir...");
    }

}