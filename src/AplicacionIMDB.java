
import java.sql.Time;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;

public class AplicacionIMDB {

	public static CatalogoIMDB catalogo;
	public static void main(String[] args){
		catalogo = CatalogoIMDB.getInstance();
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

			try {
				opcion = Integer.parseInt(sc.nextLine());
				switch(opcion) {
					//TODO Gestionar excepciones

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
						System.out.println("Introduce el nombre de la película que deseas puntuar: ");
						tituloPelicula = sc.nextLine();
						System.out.printf("Introduce la punctuation que quieres darle a %s :", tituloPelicula);
						float puntuacion = Float.parseFloat(sc.nextLine());
						catalogo.anadirVoto(tituloPelicula, puntuacion);
						System.out.printf("Has votado a %s con un %s",tituloPelicula,puntuacion);

						break;

					default:break;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("#ERROR# Dato introducido no valido, Vuelve a Introducir el numero de la opción deseada. ");
			}
			catch(NoSuchElementException | IllegalStateException e)
			{
				System.out.println("#ERROR# Imposible recorrer la linea");
			}
		}
		sc.close();
		

	}

}
