package consola;

import java.util.Scanner;

import logica.SistemaAlquiler;
import persistencia.Persistencia;

public class cEmpleado {
	static void ejecutarMenuEmpleado(SistemaAlquiler sistema, Scanner scanner) 
    {
        boolean cl_aut = false;

        while (!cl_aut)
        {
            System.out.println("Bienvenido empleado.");
            
            System.out.println("\n");
            
            System.out.println("Que desea hacer?");
            
            System.out.println("\n");
            
            System.out.println("1. Completar alquiler de reserva previa");
            System.out.println("2. Actualizar estado de un vehículo");
            System.out.println("3. Reportar mantenimiento de vehículo y reportar fecha de disponibilidad");
            System.out.println("4. Salir del menu");
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
                System.out.println("Indique la placa del carro que desea consultar: ");
                String placa = scanner.nextLine();
                sistema.agregarEventoAlHistorial(placa, "Se consultó el estado de disponibilidad del auto con placa "+placa);
                Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
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
                		sistema.agregarEventoAlHistorial(placa, "Se actualizo el estado del vechiculo con placa " + placa + " a disponible");
                		Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
                	}
                	
                	
                
	                else if (respuesta == 2) 
	                {
	                	
	                	String estatus = "Alquilado";
                		logica.SistemaAlquiler.cambiarEstado(placa, estatus);
                		
                		System.out.println("\n");
                		System.out.println("\n");
                		
                		System.out.println("Ahora el vehículo se encuentra en estado de Alquilado.");
                		sistema.agregarEventoAlHistorial(placa, "Se actualizo el estado del vechiculo con placa " + placa + " a alquilado");
                		Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
	                }
                	
                }
                
            }
                

            else if (opcion == 3) 
            {
            	System.out.println("Indique la placa del carro que desea consultar: ");
                String placa = scanner.nextLine();
                
                System.out.println("¿Desea (Si) = registrar en vehículo en mantenimiento e (No) = retirarlo?");
            	String tema = scanner.nextLine();
            	
            	if(tema.equals("Si"))
            	{
	                System.out.println("A continuación, explique brevemente el problema del vehículo.");
	                String observacion = scanner.nextLine();
	                sistema.agregarEventoAlHistorial(placa, "Se añadio la siguiente observacion al vehiculo con placa " + placa + ":" + observacion);
	                Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
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
	        		sistema.agregarEventoAlHistorial(placa, "Se actualizo el estado del vechiculo con placa " + placa + " a mantenimiento");
	        		Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
            	}
            	else if (tema.equals("No"))
            	{
            	                    
                    String estatus = "Disponible";
                    
                    String observacion = "";
                    
                    String fecha = "";
                    
                    logica.SistemaAlquiler.cambiarMantenimiento(placa, estatus, observacion, fecha);
                    
                    System.out.println("\n");
            		
            		System.out.println("Listo, el vehículo ahora se encuentra disponible.");
            		sistema.agregarEventoAlHistorial(placa, "Se actualizo el estado del vechiculo con placa " + placa + " a disponible");
            		Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
            	}
            	else
            	{
            		System.out.println("No escogió una opción valida. Vuelva a realizar el proceso.");
            	}
            }

       
            else if (opcion == 4 ) {
                cl_aut = true;
            }
            else 
            {
                System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
            }
    }
}
