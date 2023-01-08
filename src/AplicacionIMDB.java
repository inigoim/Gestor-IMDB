import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicacionIMDB {

    public static CatalogoIMDB catalogo;

    public static void main(String[] args) {
        System.out.println("¡Bienvenid@ a la aplicación de IMDB!");
        catalogo = CatalogoIMDB.getInstance();
        catalogo.cargarPeliculas("films.txt");
        catalogo.setInterpretes(new HashMapInterpretes());
        long tiempo = System.currentTimeMillis();
        catalogo.cargarInterpretes("cast.txt");
        System.out.printf("Ha tardado %,d%n", System.currentTimeMillis() - tiempo);
        String Origen;
        String Destino;
        //Menú

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        System.out.println();
        while (opcion != 0) {
            System.out.println("Escoja una opción:");
            System.out.println("1. Mostrar información de película");
            System.out.println("2. Mostrar información de intérprete");
            System.out.println("3. Añadir voto a película");
            System.out.println("4. Elimina una película");
            System.out.println("5. Calcular distancia entre dos intérpretes");
            System.out.println("6. Imprimir camino entre dos intérpretes");

            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();
                switch (opcion) {


                    case 1:
                        System.out.println("Introduce el nombre de una película:");
                        String tituloPelicula = sc.nextLine();
                        System.out.println();
                        catalogo.imprimirInfoPelicula(tituloPelicula);
                        break;

                    case 2:
                        System.out.println("Introduce el nombre de un interprete:");
                        String nombreInterprete = sc.nextLine();
                        System.out.println();
                        catalogo.imprimirInfoInterprete(nombreInterprete);
                        break;

                    case 3:
                        System.out.println("Introduzca el título de una película:");
                        tituloPelicula = sc.nextLine();
                        System.out.println("Introduzca una puntuación entre 0.0 y 10.0");
                        float puntuacion = Float.parseFloat(sc.nextLine());
                        System.out.println();
                        Pelicula pel = catalogo.peliculas.buscarPelicula(tituloPelicula);
                        if (pel == null) {
                            System.out.printf("La película %s no se encuentra en el catálogo.%n%n", tituloPelicula);
                        }
                        else {
                            pel.anadirVoto(puntuacion);
                            System.out.printf("El nuevo rating de la película es: %f%n%n", pel.getRating());
                        }

                        break;

                    case 4:
                        System.out.println("Introduzca el título de una película:");
                        tituloPelicula = sc.nextLine();
                        System.out.println();
                        if(catalogo.eliminarPelicula(tituloPelicula) == null)
                            System.out.printf("La película %s no se encuentra en el catálogo.%n%n", tituloPelicula);
                        else
                            System.out.printf("Se ha eliminado la película %s. En el catálogo quedan %,d películas y %,d" +
                                    " intérpretes.%n%n", tituloPelicula, catalogo.numPeliculas(), catalogo.numInterpretes());
                        break;
                    case 5:
                        System.out.println("Introduce el nombre del interprete origen:");
                        Origen = sc.nextLine();
                        System.out.println("Introduce el nombre del intérprete destino:");
                        Destino = sc.nextLine();
                        int distancia = catalogo.distancia(Origen,Destino);
                        if (distancia == -1) System.out.println("Los intérpretes introducidos no están conectados.");
                        else if (distancia == -10) System.out.println("No se han encontrado los interpretes introducidos");
                        else System.out.printf("La distancia entre '%s' y '%s' es de %d.%n%n", Origen, Destino, distancia);
                        break;
                    case 6:
                        System.out.println("Introduce el nombre del interprete origen:");
                        Origen = sc.nextLine();
                        System.out.println("Introduce el nombre del intérprete destino:");
                        Destino = sc.nextLine();
                        catalogo.imprimirCamino(Origen,Destino);
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
