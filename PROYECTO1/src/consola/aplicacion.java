package consola;

import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

import logica.SistemaAlquiler;
import persistencia.Persistencia;
import logica.Cliente;
import logica.Empleado;
import logica.Sede;
import logica.Seguro;
import logica.Administrador;
import logica.AdministradorLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class aplicacion {
    
    private static final Sede Null = null;

	private static Date parseFecha(String fechaStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws CsvValidationException, NumberFormatException {

        SistemaAlquiler sistema = new SistemaAlquiler();
        
        Administrador administrador = sistema.nuevoAdministrador("grupo9", "123");
        AdministradorLocal admiBogota=sistema.agregarAdmLocalBogota("admiBogota", "456",null);
        AdministradorLocal admiDorado=sistema.agregarAdmLocalDorado("admiDorado", "789",null);
        Sede sedeDorado=sistema.agregarSede("Aeropuerto El Dorado", "Avenida Calle 26 # 96A-21", admiDorado, null);
        Sede sedeBogota=sistema.agregarSede("Nuestro Bogota", "Avenida Carrera 86 # 55A-75", admiBogota, null);
        admiBogota.setSede(sedeBogota);
        admiDorado.setSede(sedeDorado);
        
        Persistencia persistencia = new Persistencia();
        persistencia.cargarDatos(sistema, "datos/vehiculos.csv", "datos/clientes.csv", "datos/empleados.csv", "datos/reservas.csv", "datos/seguros.csv"); 
        System.out.print("\nSE HAN CARGADO DATOS AUTOMATICAMENTE\n");
        
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nBienvenido/a al Sistema de Alquiler de Vehiculos!");
            System.out.println("1. Iniciar sesion como cliente");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Ingresar como administrador local");
            System.out.println("4. Iniciar sesion como empleado");
            System.out.println("5. Registrarse como cliente");
            System.out.println("6. Salir");
            System.out.println("Por favor seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.nextLine();

                System.out.print("Contrasena: ");
                String contrasena = scanner.nextLine();
                
                boolean verif = sistema.autenticarCliente(nombreUsuario, contrasena);
                
                if (verif) {
                    ejecutarMenuCliente(nombreUsuario, sistema, scanner);
            } else {
                System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
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
                System.out.print("Ingrese su nombre de usuario como administrador local: ");
                String nombreUsuario = scanner.nextLine();
                System.out.print("Ingrese su contraseña como administrador local: ");
                String contrasena = scanner.nextLine();

                List<AdministradorLocal> administradoresLocales = sistema.getAdministradoresLocales();
                AdministradorLocal admiActual = null;

                for (AdministradorLocal adminLocal : administradoresLocales) {
                    if (nombreUsuario.equals(adminLocal.getNombreUsuario()) && contrasena.equals(adminLocal.getContrasena())) {
                        admiActual = adminLocal;
                        System.out.println("\nInicio de sesión exitoso como administrador local.");
                        break; 
                    }
                }

                if (admiActual != null) {
                    ejecutarMenuAdministradorLocal(sistema, scanner, admiActual);
                } else {
                    System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                }
            } else if (opcion == 4) {
                boolean autenticado = false;               
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();
                    autenticado=sistema.autenticarEmpleado(nombreUsuario, contrasena);

					if (autenticado) {
                        System.out.println("\nInicio de sesión exitoso como empleado.");
                        ejecutarMenuEmpleado(sistema, scanner);
                        autenticado = true;
                    } else {
                        System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                    }
                }
            else if (opcion == 5) {

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

                System.out.print("Numero de la licencia de conducción: ");
                String numeroLicencia = scanner.nextLine();

                System.out.print("Pais de expedicion de la licencia de conducción: ");
                String paisExpedicionLicencia = scanner.nextLine();

                System.out.print("Fecha de vencimiento de la licencia de conducción (yyyy-MM-dd): ");
                String fechavl = scanner.nextLine();
                String fechaVencimientoLicencia = fechavl;

                System.out.print("Datos de la tarjeta de credito (numero-cvv-MM/yyyy): ");
                String datosTarjetaCredito = scanner.nextLine();

                sistema.agregarCliente(nombreUsuario, contrasena, nombre, numeroTelefonico, correo, fechaNacimiento, nacionalidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, datosTarjetaCredito);
                System.out.println("Su cuenta fue creada satisfactoriamente");
                Persistencia.escribirClientes(sistema, "datos/clientes.csv");
            } else if (opcion == 6) {
                System.out.println("Gracias por usar el Sistema de Alquiler de Vehiculos. Hasta luego!");
                salir = true; 
            }else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    private static void ejecutarMenuAdministradorLocal(SistemaAlquiler sistema, Scanner scanner, AdministradorLocal admiActual) {
		
    	 boolean salir = false;

    	    while (!salir) {
    	        System.out.println("\nMenú del Administrador Local");
    	        System.out.println("1. Ver lista de empleados de su sede");
    	        System.out.println("2. Crear un nuevo empleado para su sede");
    	        System.out.println("3. Salir del menú del Administrador Local");
    	        System.out.print("Seleccione una opción: ");

    	        int opcion = scanner.nextInt();
    	        scanner.nextLine();

    	        if (opcion == 1) {
    	            System.out.println("Lista de empleados de su sede:");
    	            List<Empleado> empleadosDeSede = sistema.getEmpleadosPorSede(admiActual.getSede());
    	            for (Empleado empleado : empleadosDeSede) {
    	                System.out.println("Nombre: " + empleado.getNombre());
    	            }
    	        } else if (opcion == 2) {

    	            System.out.println("Creación de un nuevo empleado");
    	            System.out.print("Nombre de usuario: ");
    	            String nombreUsuario = scanner.nextLine();

    	            System.out.print("Contraseña: ");
    	            String contrasena = scanner.nextLine();

    	            System.out.print("Nombre: ");
    	            String nombre = scanner.nextLine();

    	            System.out.print("Cargo: ");
    	            String cargo = scanner.nextLine();
    	            
    	            Sede sedeActual = admiActual.getSede();
    	            
    	            sistema.crearEmpleado(nombreUsuario,contrasena,nombre,cargo,sedeActual);
    	            Persistencia.escribirEmpleados(sistema, "datos/empleados.csv");
    	            
    	        } else if (opcion == 3) {
    	            salir = true;
    	        } else {
    	            System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
    	        }
    	    }
    	}
	
    	
	

	private static void ejecutarMenuCliente(String nombre, SistemaAlquiler sistema, Scanner scanner) 
    {
        boolean cl_aut = false;

        while (!cl_aut)
        {
            System.out.println("Bienvenido/a " + nombre + "!");
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
                // Hacer la extensión revisando la disponibilidad de vehículos.
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

    private static void ejecutarMenuEmpleado(SistemaAlquiler sistema, Scanner scanner) 
    {
        boolean cl_aut = false;

        while (!cl_aut)
        {
            System.out.println("Bienvenido empleado.");
            
            System.out.println("\n");
            
            System.out.println("Que desea hacer?");
            
            System.out.println("\n");
            
            System.out.println("1. Completar alquiler de reserva previa");
            System.out.println("2. Hacer alquiler sin reserva previa");
            System.out.println("3. Actualizar estado de un vehículo");
            System.out.println("4. Reportar mantenimiento de vehículo y reportar fecha de disponibilidad");
            System.out.println("5. Gestionar procesos de vehiculos");
            System.out.println("6. Salir del menu");
            System.out.println("\n");
            
            System.out.print("Seleccione una opcion: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion == 1) 
            {
            	System.out.println("Id de la reserva: ");
                String id = scanner.nextLine();
                sistema.completarAlquiler(id, scanner);
            }
            else if (opcion == 2) 
            {
                
            }
            if (opcion == 3) 
            {
                System.out.println("Indique la placa del carro que desea consultar: ");
                String placa = scanner.nextLine();
                
                String estado = logica.SistemaAlquiler.buscarDisponible(placa);
                System.out.println(estado);
                if (estado != "Placa no encontrada")
                {
                	System.out.println("El vehículo de placa " + placa  + " se encuentra en el siguiente estado: " + estado);
                	
                	
                	System.out.println("Desea actualizar su estado a (1) = Disponible o (2) = Alquilado");
                	int respuesta = scanner.nextInt();
                	
                	//DISPONIBLE
                	if (respuesta == 1)
                	{
                		String estatus = "Disponible";
                		logica.SistemaAlquiler.cambiarEstado(placa, estatus);
                		
                		System.out.println("\n");
                		System.out.println("\n");
                		
                		System.out.println("Ahora el vehículo se encuentra en estado de Disponible.");
                		
                		
                	}
                	
                	
                
	                else if (respuesta == 2) 
	                {
	                	
	                	String estatus = "Alquilado";
                		logica.SistemaAlquiler.cambiarEstado(placa, estatus);
                		
                		System.out.println("\n");
                		System.out.println("\n");
                		
                		System.out.println("Ahora el vehículo se encuentra en estado de Alquilado.");
                		
	                }
                	
                }
                
            }
                

            else if (opcion == 4) 
            {
            	System.out.println("Indique la placa del carro que desea consultar: ");
                String placa = scanner.nextLine();
                
                System.out.println("¿Desea (Si) = registrar en vehículo en mantenimiento e (No) = retirarlo?");
            	String tema = scanner.nextLine();
            	
            	if(tema.equals("Si"))
            	{
	                System.out.println("A continuación, explique brevemente el problema del vehículo.");
	                String observacion = scanner.nextLine();
	                System.out.println("\n");
	        		
	        		System.out.println("Según su criterio, ¿cuál será la fecha de reintegro del vehículo?");
	        		
	        		System.out.println("\n");
	        		System.out.println("Entiendase reintegro como el momento en el que el carro volverá a estar disponible.");
	        		String fecha = scanner.nextLine();
	        		
	        		String estado = "Mantenimiento";
	        		
	        		logica.SistemaAlquiler.cambiarMantenimiento(placa, estado, observacion, fecha);
	        		
	        		System.out.println("\n");
	        		System.out.println("\n");
	        		
	        		System.out.println("Listo, el vehículo ahora se encuentra registrado como en mantenimiento.");
            	}
            	else if (tema.equals("No"))
            	{
            	                    
                    String estatus = "Disponible";
                    
                    String observacion = "";
                    
                    String fecha = "";
                    
                    logica.SistemaAlquiler.cambiarMantenimiento(placa, estatus, observacion, fecha);
                    
                    System.out.println("\n");
            		
            		System.out.println("Listo, el vehículo ahora se encuentra disponible.");
                    
            	}
            	else
            	{
            		System.out.println("No escogió una opción valida. Vuelva a realizar el proceso.");
            	}
            }

            else if (opcion == 5 ) {
                // Terminar método de gestionar procesos.
            }
            else if (opcion == 6 ) {
                cl_aut = true;
            }
            else 
            {
                System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
            }
    }

    private static void ejecutarMenuAdministrador(SistemaAlquiler sistema) throws CsvValidationException, NumberFormatException {

        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido administrador");

            System.out.println("1. Registrar compra de nuevos vehículos");
            System.out.println("2. Dar de baja un vehículo");
            System.out.println("3. Configurar seguros");
            System.out.println("4. Salir del menú de administrador\n");
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
                Persistencia.escribirVehiculos(sistema,"datos/vehiculos.csv");
                
            } else if (opcion == 2) {
                
                System.out.print("Ingrese la placa del vehículo que desea eliminar: ");
                String placaAEliminar = scanner.nextLine();
                sistema.eliminarAuto(placaAEliminar);
                Persistencia.escribirVehiculos(sistema,"datos/vehiculos.csv");
                
            } else if (opcion == 3) {
                ejecutarMenuSeguros(sistema);
            } else if (opcion == 4) {
                salir = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void ejecutarMenuSeguros(SistemaAlquiler sistema) throws CsvValidationException, NumberFormatException {
        
        Scanner scanner = new Scanner(System.in);
        
        boolean salirSeguros = false;

        while (!salirSeguros) {
            System.out.println("\nGestión de Seguros");
            System.out.println("1. Ver seguros actuales");
            System.out.println("2. Agregar nuevo seguro");
            System.out.println("3. Eliminar seguro");
            System.out.println("4. Volver al menú principal\n");
            System.out.println("Seleccione una opción: ");

            int opcionSeguros = scanner.nextInt();
            scanner.nextLine();

            if (opcionSeguros == 1) {
                
                if (sistema.getSeguros().isEmpty()) {
                    System.out.println("No hay seguros registrados en el sistema.");
                } else {
                    System.out.println("Lista de seguros:");
                    for (Seguro seguro : sistema.getSeguros()) {
                        System.out.println("Nombre: " + seguro.getNombre() + ", Precio: " + seguro.getPrecio());
                    }
                }
            } else if (opcionSeguros == 2) {
                
                System.out.print("Nombre del nuevo seguro: ");
                String nombreSeguro = scanner.nextLine();
                System.out.print("Precio del nuevo seguro: ");
                double precioSeguro = scanner.nextInt();
                scanner.nextLine();
                sistema.agregarSeguro(nombreSeguro,precioSeguro);
                Persistencia.escribirSeguros(sistema, "datos/seguros.csv");
                
                System.out.println("Nuevo seguro agregado con éxito.");
            } else if (opcionSeguros == 3) {
                
                System.out.print("Nombre del seguro a eliminar: ");
                String nombreSeguroEliminar = scanner.nextLine();
                boolean seguroEliminado = sistema.eliminarSeguro(nombreSeguroEliminar);
                Persistencia.escribirSeguros(sistema, "datos/seguros.csv");
                Persistencia.leerSeguros(sistema, "datos/seguros.csv");
                if (seguroEliminado) {
                    System.out.println("Seguro eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un seguro con ese nombre.");
                }
            } else if (opcionSeguros == 4) {
                salirSeguros = true; 
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
