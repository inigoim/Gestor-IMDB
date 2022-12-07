import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicacionIMDB {

    public static CatalogoIMDB catalogo;

    public static void main(String[] args) {
        System.out.println("¡Bienvenid@ a la aplicación de IMDB!");
        catalogo = CatalogoIMDB.getInstance();
        catalogo.cargarPeliculas("films.txt");
        catalogo.setInterpretes(new ABBInterpretes());
        long tiempo = System.currentTimeMillis();
        catalogo.cargarInterpretes("cast.txt");
        System.out.printf("Ha tardado %,d%n", System.currentTimeMillis() - tiempo);
        //Menú

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        System.out.println();
        while (opcion != 0) {
            System.out.println("Escoja una opción:");
            System.out.println("1. Mostrar información de película");
            System.out.println("2. Mostrar información de intérprete");
            System.out.println("3. Añadir voto a película");
            System.out.println("4. Elimina una pelicula");


            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();
                switch (opcion) {


                    case 1:
                        System.out.println("Introduce el nombre de una película: ");
                        String tituloPelicula = sc.nextLine();
                        catalogo.imprimirInfoPelicula(tituloPelicula);
                        break;

                    case 2:
                        System.out.println("Introduce el nombre de un interprete: ");
                        String nombreInterprete = sc.nextLine();
                        catalogo.imprimirInfoInterprete(nombreInterprete);
                        break;

                    case 3:
                        System.out.println("Introduzca el título de una película:");
                        tituloPelicula = sc.nextLine();
                        System.out.println("Introduzca una puntuación entre 0.0 y 10.0");
                        float puntuacion = Float.parseFloat(sc.nextLine());
                        Pelicula pel = catalogo.peliculas.buscarPelicula(tituloPelicula);
                        if (pel!= null) {
                            pel.anadirVoto(puntuacion);
                            System.out.printf("El nuevo rating de la película es: %f%n", pel.getRating());
                        }
                        else
                            System.out.println("La película introducida no está en la base de datos.");

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
                System.out.println("#ERROR# Dato introducido no valido, Vuelve a Introducir el numero de la opción deseada. ");
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("#ERROR# Imposible recorrer la linea");
            }
        }
        sc.close();


    }

}
