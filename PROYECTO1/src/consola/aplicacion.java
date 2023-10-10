package consola;

import java.util.Scanner;
import logica.SistemaAlquiler;
import logica.Cliente;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class aplicacion {
	
	private static Date parseFecha(String fechaStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        SistemaAlquiler sistema = new SistemaAlquiler();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al Sistema de Alquiler de Vehiculos");
            System.out.println("1. Iniciar sesion como cliente");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Iniciar sesion como empleado");
            System.out.println("4. Registrarse como cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
            	
            	System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.nextLine();

                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine();
                
                boolean verif = sistema.autenticarCliente(nombreUsuario, contrasena);
            	
            } else if (opcion == 2) {

            } else if (opcion == 3) {
                
            } else if (opcion == 4) {
            
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
                Date fechaNacimiento = parseFecha(fechaNacimientoStr);
                
                System.out.print("Nacionalidad: ");
                String nacionalidad = scanner.nextLine();
                
                String imagenDocumento = nombreUsuario + ".pdf";
                
                System.out.print("Numero de la licencia de conducción: ");
                String numeroLicencia = scanner.nextLine();
                
                System.out.print("Pais de expedicion de la licencia de conducción: ");
                String paisExpedicionLicencia = scanner.nextLine();
                
                System.out.print("Fecha de vencimiento de la licencia de conducción (yyyy-MM-dd): ");
                String fechavl = scanner.nextLine();
                Date fechaVencimientoLicencia = parseFecha(fechavl);
                
                System.out.print("Datos de la tarjeta de credito (numero-cvv-MM/yyyy): ");
                String datosTarjetaCredito = scanner.nextLine();
                
                Cliente datos = new Cliente(nombreUsuario,contrasena, nombre, numeroTelefonico,correo,
                   fechaNacimiento, nacionalidad, imagenDocumento,
                   numeroLicencia, paisExpedicionLicencia,
                   fechaVencimientoLicencia,datosTarjetaCredito);
                sistema.agregarCliente(datos);
                System.out.println("Su cuenta fue creada satisfactoriamente");
                
                
            } else if (opcion == 5) {
                System.out.println("Gracias por usar el Sistema de Alquiler de Vehiculos. Hasta luego!");
                salir = true; 
            } else {
                System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
        }
        

        scanner.close();
    }
}
