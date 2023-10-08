package consola;

import java.util.Scanner;
import logica.SistemaAlquiler;

public class aplicacion {

    public static void main(String[] args) {

        SistemaAlquiler sistema = new SistemaAlquiler();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al Sistema de Alquiler de Vehículos");
            System.out.println("1. Iniciar sesión como cliente");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Iniciar sesión como empleado");
            System.out.println("4. Registrarse como cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
            } else if (opcion == 2) {

            } else if (opcion == 3) {
                
            } else if (opcion == 4) {
                
            } else if (opcion == 5) {
                System.out.println("Gracias por usar el Sistema de Alquiler de Vehículos. ¡Hasta luego!");
                salir = true; 
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }
}
