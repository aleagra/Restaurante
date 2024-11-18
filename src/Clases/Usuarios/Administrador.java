package Clases.Usuarios;

import Clases.Gestion.Bebida;
import Clases.Gestion.Plato;
import ClasesGestoras.Carta;
import Enums.TipoDeBebida;
import Enums.TipoDePlato;

import java.util.Scanner;

public class Administrador extends Usuario{

    public Administrador(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
    }

    @Override
    public boolean cambiarContrasenia() {
        return false;
    }

    public void gestionarMenu(Carta carta){
      int opcion = 0;
      Scanner sc = new Scanner(System.in);

      do{
          System.out.println("1) Si desea agregar un plato");
          System.out.println("2) Si desea agregar una bebida");
          System.out.println("3) Si desea eliminar una comida");
          System.out.println("3) Si desea eliminar una bebida");
          opcion = sc.nextInt();
          switch(opcion){
              case 1:
                  {
                      int opccate=0;
                      Plato pl = new Plato();
                      System.out.println("Ingrese el nombre del plato");
                      pl.setNombre(sc.next());
                      System.out.println("Ingrese la descripcion del plato");
                      pl.setDescripcion(sc.next());
                      System.out.println("Ingrese el precio del plato");
                      pl.setPrecio(sc.nextDouble());
                      System.out.println("Que categoria es su plato?");
                      System.out.println("1) Entrante");
                      System.out.println("1) Principal");
                      System.out.println("1) Postre");
                      opccate = sc.nextInt();
                      switch(opccate){
                          case 1:{pl.setCategoria(TipoDePlato.ENTRANTE); break;}
                          case 2:{pl.setCategoria(TipoDePlato.PRINCIPAL);break;}
                          case 3:{pl.setCategoria(TipoDePlato.POSTRE);break;}
                      }
                      carta.agregarComida(pl);

              }
              case 2:{
                  int opccate= 0;
                  Bebida beb = new Bebida();
                  System.out.println("Ingrese el nombre del bebida");
                  beb.setNombre(sc.next());
                  System.out.println("Ingrese el precio de la bebida");
                  beb.setPrecio(sc.nextDouble());
                  System.out.println("Que categoria es su bebida?");
                  System.out.println("1) Con alcohol");
                  System.out.println("1) Sin alcohol");
                  opccate = sc.nextInt();
                  switch (opccate){
                      case 1:{beb.setTipo(TipoDeBebida.CON_ALCOHOL); break;}
                      case 2:{beb.setTipo(TipoDeBebida.SIN_ALCOHOL); break;}
                  }
                  carta.agregarBebida(beb);
              }

              case 3:{
                  String name = " ";
                  Plato plato = new Plato();
                  System.out.println("Ingrese el nombre del plato que desea elminar: ");
                  plato.setNombre(sc.next());
                  carta.eliminarComida(plato);
              }
              case 4: {
                  Bebida bl = new Bebida();
                  System.out.println("Ingrese el nombre del bebida");
                  bl.setNombre(sc.next());
                  carta.eliminarBebida(bl);
              }
          }
      }while(opcion != 0);
    }

    public void crearPromocion(){

    }

    public void verReservas(){

    }
}
