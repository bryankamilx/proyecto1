package consola;

import java.util.Scanner;
import logica.SistemaAlquiler;
import logica.Cliente;
import logica.Administrador;
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
        Administrador administrador = sistema.nuevoAdministrador("grupo9", "123");


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

                System.out.print("Contrasena: ");
                String contrasena = scanner.nextLine();
                
                boolean verif = sistema.autenticarCliente(nombreUsuario, contrasena);
                
                if (verif == true) 
                {
                	ejecutarMenuCliente(nombreUsuario, sistema, scanner);
                }
            	
            } else if (opcion == 2) {
            	boolean autenticado = false;
                while (!autenticado) {
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();

                    if (nombreUsuario.equals(administrador.getNombreUsuario()) && contrasena.equals(administrador.getContrasena())) {

                        System.out.println("\nInicio de sesión exitoso como administrador.");
                        ejecutarMenuAdministrador(sistema);
                        autenticado = true;

                    } else {
                        System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                    }
                }


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
    
    private static void ejecutarMenuCliente(String nombre, SistemaAlquiler sistema, Scanner scanner) 
    {
    	boolean cl_aut = false;
    	
    	while (!cl_aut)
    	{
    		System.out.println("Bienvenido " + nombre);
    		System.out.println("1. Realizar una reserva");
    		System.out.println("2. Extender un alquiler");
    		System.out.println("3. Salir del menu");
    		System.out.print("Seleccione una opcion: ");
    		
    		int opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion == 1) 
            {
            	sistema.realizarReserva(nombre, scanner);
            }
            
            else if (opcion == 2 ) {
            	//hacer la extension revisando la disponibilidad de vehiculos.
            }
            
            else if (opcion == 3 ) 
            {
            	cl_aut = true;
            }
            else 
            {
                System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
    		
    	}
    }
    
    private static void ejecutarMenuAdministrador(SistemaAlquiler sistema) {

        System.out.println("1. Registrar compra de nuevos vehículos");
        System.out.println("2. Dar de baja un vehículo");
        System.out.println("3. Configurar seguros");
        System.out.println("4. Acceso a las sedes");
        System.out.println("5. Crear usuario de empleado");

        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido administrador");

            System.out.println("1. Registrar compra de nuevos vehículos");
            System.out.println("2. Dar de baja un vehículo");
            System.out.println("3. Configurar seguros");
            System.out.println("4. Acceso a las sedes");
            System.out.println("5. Crear usuario de empleado");
            System.out.println("6. Salir del menú de administrador\n");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {

                System.out.println("Ingrese los detalles del nuevo vehículo:\n");

                System.out.print("Placa: ");
                String placa = scanner.nextLine();

                System.out.print("Marca: ");
                String marca = scanner.nextLine();

                System.out.print("Modelo: ");
                String modelo = scanner.nextLine();

                System.out.print("Color: ");
                String color = scanner.nextLine();

                System.out.print("Transmisión: ");
                String transmision = scanner.nextLine();

                System.out.print("Categoría: ");
                String categoria = scanner.nextLine();
                
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                
                System.out.print("Pasajeros: ");
                String pasajeros = scanner.nextLine();
                
                System.out.print("Tarifa: ");
                String tarifa = scanner.nextLine();
                

                sistema.agregarVehiculo(placa, marca, modelo, color, transmision, categoria, estado, pasajeros, tarifa);
//                sistema.getVehiculos();

            } else if (opcion == 2) {
                // Lógica para dar de baja un vehículo
            } else if (opcion == 3) {
                // Lógica para configurar seguros
            } else if (opcion == 4) {
                // Lógica para el acceso a las sedes
            } else if (opcion == 5) {
                // Lógica para crear usuario de empleado
            } else if (opcion == 6) {
                salir = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
        scanner.close();
    }

}
