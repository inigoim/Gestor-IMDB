
import java.util.Scanner;

public class AplicacionIMDB {

	public static CatalogoIMDB catalogo;
	public static void main(String[] args){
		catalogo = catalogo.getInstance();
          //TO DO: ...
		
	    //TO DO: Cargar películas	
		catalogo.cargarPeliculas("films.txt");

	    //TO DO Cargar intérpretes
		catalogo.cargarInterpretes("cast.txt");
		
		//Menú

		Scanner sc = new Scanner(System.in);
		int opcion=-1;
		
		while(opcion!=0) {
			System.out.println("Escoja una opción:");
			System.out.println("1. Mostrar información de película");
			System.out.println("2. Mostrar información de intérprete");
			System.out.println("3. Añadir voto a película");

			System.out.println("0. Salir");
			opcion = Integer.parseInt(sc.nextLine());
			switch(opcion) {

				case 1:
					System.out.println("Introduce el nombre de una película: ");
					String tituloPelicula = sc.nextLine();
					catalogo.imprimirInfoPelicula(tituloPelicula);
					break;

				case 2:
					System.out.println("Introduce el nombre de un interprete: ");
					String nombreInterprete = sc.nextLine();
					catalogo.imprimirInfoPelicula(nombreInterprete);
					break;

				case 3:
					System.out.println("Introduce el nombre de la película que deseas puntuar: ");
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
