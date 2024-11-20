package Menus;

import Clases.Gestion.Bebida;
import Clases.Gestion.Cuenta;
import Clases.Gestion.Plato;
import Clases.Usuarios.Administrador;
import ClasesGestoras.Carta;
import ClasesGestoras.Reservas;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;
import Excepciones.MenuException;

import java.util.Scanner;

public class MenuAdmin {
    public boolean mostrarMenu(Administrador administrador, Cuenta cuenta, Carta c, Reservas r) throws MenuException {
        int opc = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Menu Admin");
            System.out.println("1- Cambiar contrasenia");
            System.out.println("2- Gestionar el menu");
            System.out.println("4- Visualizar reservas ");
            System.out.println("0- Salir ");
            opc = sc.nextInt();
            sc.nextLine();
            switch(opc){
                case 1:{
                    System.out.println("Cambiando contrasenia...");
                    System.out.println("Ingrese la contrasenia actual: ");
                    String contraActual = sc.nextLine();
                    System.out.println("Ingrese la contrasenia nueva: ");
                    String contraNueva = sc.nextLine();
                    administrador.cambiarContrasenia(contraActual, contraNueva);
                }
                case 2:{
                    int opcion = 0;
                    do {
                        System.out.println("Gestión del Menú");
                        System.out.println("1) Agregar un plato");
                        System.out.println("2) Agregar una bebida");
                        System.out.println("3) Eliminar un plato");
                        System.out.println("4) Eliminar una bebida");
                        System.out.println("0) Volver al menú principal");
                        opcion = sc.nextInt();
                        sc.nextLine();

                        switch (opcion) {
                            case 1: {
                                int opccate;
                                Plato pl = new Plato();
                                System.out.println("Ingrese el nombre del plato:");
                                pl.setNombre(sc.nextLine());
                                System.out.println("Ingrese la descripción del plato:");
                                pl.setDescripcion(sc.nextLine());
                                System.out.println("Ingrese el precio del plato:");
                                pl.setPrecio(sc.nextDouble());
                                sc.nextLine();
                                System.out.println("Seleccione la categoría:");
                                System.out.println("1) Entrante");
                                System.out.println("2) Principal");
                                System.out.println("3) Postre");
                                opccate = sc.nextInt();
                                sc.nextLine();
                                switch (opccate) {
                                    case 1: {
                                        pl.setCategoria(TipoDePlato.ENTRANTE);
                                        break;
                                    }
                                    case 2: {
                                        pl.setCategoria(TipoDePlato.PRINCIPAL);
                                        break;
                                    }
                                    case 3: {
                                        pl.setCategoria(TipoDePlato.POSTRE);
                                        break;
                                    }
                                }

                                if (administrador.gestionarMenu(1, c, pl, null)){
                                    System.out.println("Plato agregado correctamente");
                                }else {
                                    throw new MenuException("El plato no pudo ser agregado");
                                }


                                break;
                            }
                            case 2: {
                                Bebida beb = new Bebida();
                                System.out.println("Ingrese el nombre de la bebida:");
                                beb.setNombre(sc.nextLine());
                                System.out.println("Ingrese el precio de la bebida:");
                                beb.setPrecio(sc.nextDouble());
                                sc.nextLine();
                                System.out.println("Seleccione el tipo de bebida:");
                                System.out.println("1) Con alcohol");
                                System.out.println("2) Sin alcohol");
                                int tipoBebida = sc.nextInt();
                                sc.nextLine();
                                switch (tipoBebida) {
                                    case 1:
                                        beb.setTipo(TipoDeBebida.CON_ALCOHOL);
                                    case 2:
                                        beb.setTipo(TipoDeBebida.SIN_ALCOHOL);
                                }
                                if(administrador.gestionarMenu(2, c, null, beb)){
                                    System.out.println("Bebida agregada con exito");
                                }else{
                                    throw new MenuException("El plato no pudo ser agregado");
                                }
                                break;

                            }
                            case 3: {
                                System.out.println("Ingrese el nombre del plato a eliminar:");
                                String nombre = sc.nextLine();
                                Plato plato = new Plato();
                                plato.setNombre(nombre);
                                if(administrador.gestionarMenu(3, c, plato, null)){
                                    System.out.println("Plato eliminado correctamente");
                                }else{
                                    throw new MenuException("El plato no pudo ser eliminado");
                                }

                                break;

                            }
                            case 4: {
                                System.out.println("Ingrese el nombre de la bebida a eliminar:");
                                String nombre = sc.nextLine();
                                Bebida bebida = new Bebida();
                                bebida.setNombre(nombre);
                                if(administrador.gestionarMenu(4, c, null, bebida)){
                                    System.out.println("Bebida eliminado correctamente");
                                }else {
                                    throw new MenuException("La bebida no pudo ser eliminada");
                                }
                                break;
                            }

                        }


                    }while(opcion!=0);
                }
            }
        }while(opc != 0);

        return false;
    }
}
