package interfaz;

import java.util.Scanner;

import logica.SistemaAlquiler;
import persistencia.Persistencia;

public class cCliente {

	static void ejecutarMenuCliente(String nombre, SistemaAlquiler sistema, Scanner scanner) 
    {
        boolean cl_aut = false;

        while (!cl_aut)
        {
            System.out.println("Bienvenido/a " + nombre + "!");
            System.out.println("1. Realizar una reserva");
            System.out.println("2. Salir del menu");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) 
            {
                sistema.realizarReserva(nombre, scanner);
            }
            else if (opcion == 2 ) 
            {
                cl_aut = true;
            }
            else 
            {
                System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
        }
    }

	public static void crearCliente(SistemaAlquiler sistema, Scanner scanner) {
		System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Numero telefonico: ");
        String numeroTelefonico = scanner.nextLine();

        System.out.print("Correo electronico: ");
        String correo = scanner.nextLine();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        String fechaNacimientoStr = scanner.nextLine();
        String fechaNacimiento = fechaNacimientoStr;

        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Numero de la licencia de conduccion: ");
        String numeroLicencia = scanner.nextLine();

        System.out.print("Pais de expedicion de la licencia de conduccion: ");
        String paisExpedicionLicencia = scanner.nextLine();

        System.out.print("Fecha de vencimiento de la licencia de conduccion (yyyy-MM-dd): ");
        String fechavl = scanner.nextLine();
        String fechaVencimientoLicencia = fechavl;

        System.out.print("Datos de la tarjeta de credito (numero-cvv-MM/yyyy): ");
        String datosTarjetaCredito = scanner.nextLine();

        sistema.agregarCliente(nombreUsuario, contrasena, nombre, numeroTelefonico, correo, fechaNacimiento, nacionalidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, datosTarjetaCredito);
        System.out.println("Su cuenta fue creada satisfactoriamente");
        Persistencia.escribirClientes(sistema, "datos/clientes.csv");
	}
}
