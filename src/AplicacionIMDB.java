import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicacionIMDB {

    public static CatalogoIMDB catalogo;

    public static void main(String[] args) {
        System.out.println("�Bienvenid@ a la aplicaci�n de IMDB!");
        catalogo = CatalogoIMDB.getInstance();
        catalogo.cargarPeliculas("films.txt");
        catalogo.setInterpretes(new HashMapInterpretes());
        long tiempo = System.currentTimeMillis();
        catalogo.cargarInterpretes("cast.txt");
        System.out.printf("Ha tardado %,d%n", System.currentTimeMillis() - tiempo);
        String Origen;
        String Destino;
        //Men�

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        System.out.println();
        while (opcion != 0) {
            System.out.println("Escoja una opci�n:");
            System.out.println("1. Mostrar informaci�n de pel�cula");
            System.out.println("2. Mostrar informaci�n de int�rprete");
            System.out.println("3. A�adir voto a pel�cula");
            System.out.println("4. Elimina una pel�cula");
            System.out.println("5. Calcular distancia entre dos int�rpretes");
            System.out.println("6. Imprimir camino entre dos int�rpretes");

            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();
                switch (opcion) {


                    case 1:
                        System.out.println("Introduce el nombre de una pel�cula:");
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
                        System.out.println("Introduzca el t�tulo de una pel�cula:");
                        tituloPelicula = sc.nextLine();
                        System.out.println("Introduzca una puntuaci�n entre 0.0 y 10.0");
                        float puntuacion = Float.parseFloat(sc.nextLine());
                        System.out.println();
                        Pelicula pel = catalogo.peliculas.buscarPelicula(tituloPelicula);
                        if (pel == null) {
                            System.out.printf("La pel�cula %s no se encuentra en el cat�logo.%n%n", tituloPelicula);
                        }
                        else {
                            pel.anadirVoto(puntuacion);
                            System.out.printf("El nuevo rating de la pel�cula es: %f%n%n", pel.getRating());
                        }

                        break;

                    case 4:
                        System.out.println("Introduzca el t�tulo de una pel�cula:");
                        tituloPelicula = sc.nextLine();
                        System.out.println();
                        if(catalogo.eliminarPelicula(tituloPelicula) == null)
                            System.out.printf("La pel�cula %s no se encuentra en el cat�logo.%n%n", tituloPelicula);
                        else
                            System.out.printf("Se ha eliminado la pel�cula %s. En el cat�logo quedan %,d pel�culas y %,d" +
                                    " int�rpretes.%n%n", tituloPelicula, catalogo.numPeliculas(), catalogo.numInterpretes());
                        break;
                    case 5:
                        System.out.println("Introduce el nombre del interprete origen:");
                        Origen = sc.nextLine();
                        System.out.println("Introduce el nombre del int�rprete destino:");
                        Destino = sc.nextLine();
                        int distancia = catalogo.distancia(Origen,Destino);
                        if (distancia == -1) System.out.println("Los int�rpretes introducidos no est�n conectados.");
                        else if (distancia == -10) System.out.println("No se han encontrado los interpretes introducidos");
                        else System.out.printf("La distancia entre '%s' y '%s' es de %d.%n%n", Origen, Destino, distancia);
                        break;
                    case 6:
                        System.out.println("Introduce el nombre del interprete origen:");
                        Origen = sc.nextLine();
                        System.out.println("Introduce el nombre del int�rprete destino:");
                        Destino = sc.nextLine();
                        catalogo.imprimirCamino(Origen,Destino);
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
