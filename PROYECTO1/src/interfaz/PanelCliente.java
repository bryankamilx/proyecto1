package interfaz;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import logica.*;
import persistencia.Persistencia;

public class PanelCliente {

	public static void ejecutarMenuCliente(String nombre, SistemaAlquiler sistema, Scanner scanner) 
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
	
	public static void completarDatosReserva (String nombre, List<List<String>> listaDeListas, Scanner scanner) {
		boolean bandera = false;
		int i = 0;
		
		while (i<listaDeListas.size()) {
			List<String> categoria = listaDeListas.get(i);
			System.out.println("Opcion " + String.valueOf(i+1));
            System.out.println("Categoria: " + categoria.get(0));            
            System.out.println("Tarifa Diaria: " + categoria.get(1));
            System.out.println("Numero de Pasajeros: " + categoria.get(0));
            System.out.println();
            i++;
		}
		
		
		
		while (!bandera) {
	           
            List<String> selec = null;
            String fechaHoraStr1 = "";
            String fechaHoraStr2 = "";
            double diferenciaEnDias = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            System.out.println("Seleccione una opcion entre 1 y 16: ");
    		
    		int opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion >= 1 && opcion <= 16) {
            	selec = listaDeListas.get(opcion-1);	
            }
            try {
                System.out.println("Ingrese la fecha y hora de inicio del alquiler (yyyy-MM-dd HH:mm): ");
                fechaHoraStr1 = scanner.nextLine();
                Date fechaHora1 = dateFormat.parse(fechaHoraStr1);
                
                System.out.println("Tenga en cuenta que si elige una hora de finalizacion mas tarde que la de inicio se cobra un dia adicional");
                System.out.println("Ingrese la fecha y hora de finalizacion del alquiler (yyyy-MM-dd HH:mm): ");
                fechaHoraStr2 = scanner.nextLine();
                Date fechaHora2 = dateFormat.parse(fechaHoraStr2);

                long diferenciaEnMilisegundos = fechaHora2.getTime() - fechaHora1.getTime();

                diferenciaEnDias = Math.ceil(diferenciaEnMilisegundos / (1000.0 * 60 * 60 * 24));

                System.out.println("Dias totales: " + (int) diferenciaEnDias);
            } catch (ParseException e) {
                System.out.println("Error al analizar las fechas. Asegurate de usar el formato yyyy-MM-dd HH:mm.");
            }
            
            
            
            System.out.println("En que sede le gustaria recoger el vehiculo?");
            List<String> sederec = PanelCliente.seleccionarSede(scanner);
            System.out.println("En que sede le gustaria entregar el vehiculo?");
            System.out.println("(Entregarlo en una sede diferente a la cual lo recibio tiene un costo adicional)");
            List<String> sedeent = PanelCliente.seleccionarSede(scanner);
            
            String idres = (UUID.randomUUID()).toString();
            
            SistemaAlquiler.cargarReserva(idres,selec,nombre,sederec,sedeent,fechaHoraStr1,fechaHoraStr2,diferenciaEnDias);
            
            bandera= true;
            
            
            }
		
		
	}
	public static List<String> seleccionarSede(Scanner scanner) {
	    	
	    	try (CSVReader reader = new CSVReader(new FileReader("datos/sedes.csv"))) {
	    		List<List<String>> listaDeListas = new ArrayList<>();
	            boolean primeraLinea = true;
	
	            while (true) {
	                String[] linea = reader.readNext();
	                if (linea == null) {
	                    break; // Fin del archivo
	                }
	
	                if (primeraLinea) {
	                    primeraLinea = false;
	                    continue;
	                }
	                
	                List<String> lista = new ArrayList<>();
	                String nombre = linea[0];
	                String direccion = linea[1];
	                String horario = linea[2];
	                
	                
	                lista.add(nombre);
	                lista.add(direccion);
	                lista.add(horario);
	                
	                listaDeListas.add(lista);
	            }
	            int totalSedes = listaDeListas.size();
	
	            if (totalSedes > 0) {
	                System.out.println("Sedes disponibles:");
	                for (int i = 0; i < totalSedes; i++) {
	                    List<String> sede = listaDeListas.get(i);
	                    System.out.println("Opcion " + (i + 1));
	                    System.out.println("Nombre: " + sede.get(0));
	                    System.out.println("Direccion: " + sede.get(1));
	                    System.out.println("Horario: " + sede.get(2));
	                    System.out.println();
	                }
	
	                System.out.println("Seleccione una opcion entre 1 y " + totalSedes + ": ");
	                int opcion = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                
	                if (opcion >= 1 && opcion <= totalSedes) {
	                    return listaDeListas.get(opcion - 1);
	                    
	                } else {
	                    System.out.println("Opcion invalida.");
	                }
	            } else {
	                System.out.println("No hay sedes disponibles.");
	            }
	            
	        } catch (IOException | CsvValidationException e) {
	            e.printStackTrace();
	        }
	
	        return null;
	    	}
	
	
	
}
