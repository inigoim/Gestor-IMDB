import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicacionIMDB {

    public static CatalogoIMDB catalogo;

    public static void main(String[] args) {
        System.out.println("�Bienvenid@ a la aplicaci�n de IMDB!");
        catalogo = CatalogoIMDB.getInstance();
        catalogo.cargarPeliculas("films.txt");
        catalogo.setInterpretes(new ABBInterpretes());
        long tiempo = System.currentTimeMillis();
        catalogo.cargarInterpretes("cast.txt");
        System.out.printf("Ha tardado %,d%n", System.currentTimeMillis() - tiempo);
        //Men�

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        System.out.println();
        while (opcion != 0) {
            System.out.println("Escoja una opci�n:");
            System.out.println("1. Mostrar informaci�n de pel�cula");
            System.out.println("2. Mostrar informaci�n de int�rprete");
            System.out.println("3. A�adir voto a pel�cula");
            System.out.println("4. Elimina una pelicula");


            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();
                switch (opcion) {


                    case 1:
                        System.out.println("Introduce el nombre de una pel�cula: ");
                        String tituloPelicula = sc.nextLine();
                        catalogo.imprimirInfoPelicula(tituloPelicula);
                        break;

                    case 2:
                        System.out.println("Introduce el nombre de un interprete: ");
                        String nombreInterprete = sc.nextLine();
                        catalogo.imprimirInfoInterprete(nombreInterprete);
                        break;

                    case 3:
                        System.out.println("Introduzca el t�tulo de una pel�cula:");
                        tituloPelicula = sc.nextLine();
                        System.out.println("Introduzca una puntuaci�n entre 0.0 y 10.0");
                        float puntuacion = Float.parseFloat(sc.nextLine());
                        Pelicula pel = catalogo.peliculas.buscarPelicula(tituloPelicula);
                        if (pel!= null) {
                            pel.anadirVoto(puntuacion);
                            System.out.printf("El nuevo rating de la pel�cula es: %f%n", pel.getRating());
                        }
                        else
                            System.out.println("La pel�cula introducida no est� en la base de datos.");

                        break;

                    case 4:
                        System.out.println("Introduce el nombre de la pelicula que deseas eliminar");
                        tituloPelicula = sc.nextLine();
                        if(catalogo.eliminarPelicula(tituloPelicula)==null){
                            System.out.println("La pelicula no esta en el catalogo");
                            break;
                        }
                        catalogo.eliminarPelicula(tituloPelicula);
                        break;

                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("#ERROR# Dato introducido no valido, Vuelve a Introducir el numero de la opci�n deseada. ");
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("#ERROR# Imposible recorrer la linea");
            }
        }
        sc.close();


    }

}
