package fase1;


import java.util.Scanner;

public class AplicacionIMDB {
	
	public static void main(String[] args){

          //TO DO: ...
		
	    //TO DO: Cargar películas	

	    //TO DO Cargar intérpretes
	
		
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
				    //TO DO
			        break;
	              // TO DO
			}
		}
		sc.close();
		

	}

}
