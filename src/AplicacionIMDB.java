
import java.sql.Time;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;

public class AplicacionIMDB {

	public static CatalogoIMDB catalogo;
	public static void main(String[] args){
		catalogo = CatalogoIMDB.getInstance();
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

			try {
				opcion = Integer.parseInt(sc.nextLine());
				switch(opcion) {
					//TODO Gestionar excepciones

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
						System.out.println("Introduce el nombre de la pel�cula que deseas puntuar: ");
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
				System.out.println("#ERROR# Dato introducido no valido, Vuelve a Introducir el numero de la opci�n deseada. ");
			}
			catch(NoSuchElementException | IllegalStateException e)
			{
				System.out.println("#ERROR# Imposible recorrer la linea");
			}
		}
		sc.close();
		

	}

}
