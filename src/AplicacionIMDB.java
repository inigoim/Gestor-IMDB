package fase1;


import java.util.Scanner;

public class AplicacionIMDB {
	
	public static void main(String[] args){

          //TO DO: ...
		
	    //TO DO: Cargar pel�culas	

	    //TO DO Cargar int�rpretes
	
		
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
				    //TO DO
			        break;
	              // TO DO
			}
		}
		sc.close();
		

	}

}
