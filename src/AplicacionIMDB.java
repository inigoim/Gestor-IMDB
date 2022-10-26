
import java.util.Scanner;

public class AplicacionIMDB {

	public static CatalogoIMDB catalogo;
	public static void main(String[] args){
		catalogo = catalogo.getInstance();
          //TO DO: ...
		
	    //TO DO: Cargar pel�culas	
		catalogo.cargarPeliculas("films.txt");

	    //TO DO Cargar int�rpretes
		catalogo.cargarInterpretes("cast.txt");
		
		//Men�

		Scanner sc = new Scanner(System.in);
		int opcion=-1;
		
		while(opcion!=0) {
			System.out.println("Escoja una opci�n:");
			System.out.println("1. Mostrar informaci�n de pel�cula");
			System.out.println("2. Mostrar informaci�n de int�rprete");
			System.out.println("3. A�adir voto a pel�cula");

			System.out.println("0. Salir");
			opcion = Integer.parseInt(sc.nextLine());
			switch(opcion) {

				case 1:
					System.out.println("Introduce el nombre de una pel�cula: ");
					String tituloPelicula = sc.nextLine();
					catalogo.imprimirInfoPelicula(tituloPelicula);
					break;

				case 2:
					System.out.println("Introduce el nombre de un interprete: ");
					String nombreInterprete = sc.nextLine();
					catalogo.imprimirInfoPelicula(nombreInterprete);
					break;

				case 3:
					System.out.println("Introduce el nombre de la pel�cula que deseas puntuar: ");
					tituloPelicula = sc.nextLine();
					System.out.println("Introduce la punctuation que quieres darle a " + tituloPelicula + ": ");
					float puntuacion = Float.parseFloat(sc.nextLine());
					catalogo.anadirVoto(tituloPelicula, puntuacion);
					break;

				default:break;
			}
		}
		sc.close();
		

	}

}
