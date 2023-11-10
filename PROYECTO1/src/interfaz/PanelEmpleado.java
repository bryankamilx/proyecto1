package interfaz;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import logica.ConductorAdicional;
import logica.SistemaAlquiler;
import logica.Vehiculo;
import persistencia.Persistencia;

public class PanelEmpleado {
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
	
	public static void completarAlquiler(String id, Scanner scanner) {
		try (CSVReader reader = new CSVReader(new FileReader("datos/reservas.csv"))) {
            
            int indiceId = 0;
            int indiceCategoria = 1;
            int indiceUsuario = 2;
            int indiceSedeRecogida = 3;
            int indiceSedeEntrega = 4;
            int indiceInicio = 5;
            int indiceFinal = 6;
            int indiceDias = 7;
            int indiceCostoTotal = 8;
            int indiceTreinta = 9;

            
            
            String[] fila;
            while ((fila = reader.readNext()) != null) {
                String reservaId = fila[indiceId];

                if (reservaId.equals(id)) {
                	String categoria = fila[indiceCategoria];
                	String usuario = fila[indiceUsuario];
                	String recogida = fila[indiceSedeRecogida];
                	String entrega = fila[indiceSedeEntrega];
                	String inicio = fila[indiceInicio];
                	String ffinal = fila[indiceFinal];
                	String dias = fila[indiceDias];
                	String costo = fila[indiceCostoTotal];
                	String treinta = fila[indiceTreinta];
                	
                	
                	List<String> escoger =Vehiculo.escogerCarro(categoria, inicio, ffinal);
                	
               	 	String categoriaEsc = escoger.get(0);
               	 	String placa = escoger.get(1);
               	 	
               	 	
                    List<String> seguro= escogerSeguro(scanner);
                    System.out.println("Desea agregar conductores adicionales? Escriba 1 para si, 0 para no: ");
                    String pregunta = scanner.nextLine();
                    int numeroAdicionales= 0;
                    if (pregunta.equals("1")) {
                    numeroAdicionales= ConductorAdicional.conductoresAgregados(scanner, usuario, id);}
                    else {
                    	System.out.println("Generando archivo de alquiler");
                    }
                    double valorTotal = ((Double.parseDouble(costo) - Double.parseDouble(treinta)) + (Double.parseDouble(seguro.get(1)) * Double.parseDouble(dias)));
                    
                    String rutaCompleta = "datos/alquileres.csv";
                    boolean archivoExiste = new File(rutaCompleta).exists();

                    List<String> nuevaFila = new ArrayList<>();
                    
                    nuevaFila.add(id);
                    nuevaFila.add(usuario);
                    nuevaFila.add(categoriaEsc);
                    nuevaFila.add(placa);
                    nuevaFila.add(recogida);
                    nuevaFila.add(entrega);
                    nuevaFila.add(inicio);
                    nuevaFila.add(ffinal);
                    nuevaFila.add(dias);
                    nuevaFila.add(Double.toString(valorTotal));
                    nuevaFila.add(Integer.toString(numeroAdicionales));
                    nuevaFila.add(seguro.get(0));
                 

                    try (CSVWriter writer = new CSVWriter(new FileWriter(rutaCompleta, true))) {
                        if (archivoExiste==false) {
                            String[] encabezados = {"Id alquiler", "Usuario alquiler", "Categoria vehiculo",
                           		 "Placa", "Sede de recogida", "Sede de entrega", "Fecha de inicio", 
                           		 "Fecha finalizacion", "Dias en total", "Costo del excedente mas el seguro",
                           		 "Conductores adicionales","Seguro seleccionado"};
                            writer.writeNext(encabezados);
                        }
                        writer.writeNext(nuevaFila.toArray(new String[0]));
                       
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("El archivo de alquiler fue agregado correctamente");
                    
                   
                }
            }
            
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
	}
	
	public static List<String> escogerSeguro (Scanner scanner){
		try (CSVReader reader = new CSVReader(new FileReader("datos/seguros.csv"))) {
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
                String valor = linea[1];
                String descripcion = linea[2];
                
                
                lista.add(nombre);
                lista.add(valor);
                lista.add(descripcion);
                
                listaDeListas.add(lista);
            }
            int totalSedes = listaDeListas.size();

            if (totalSedes > 0) {
                System.out.println("Seguros disponibles:");
                for (int i = 0; i < totalSedes; i++) {
                    List<String> sede = listaDeListas.get(i);
                    System.out.println("Opcion " + (i + 1));
                    System.out.println("Nombre: " + sede.get(0));
                    System.out.println("Valor por dia: " + sede.get(1));
                    System.out.println("Descripcion: " + sede.get(2));
                    System.out.println();
                }

                System.out.println("Seleccione una opcion entre 1 y " + totalSedes + " dependiendo de la seleccion del cliente: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion >= 1 && opcion <= totalSedes) {
                    return listaDeListas.get(opcion - 1);
                    
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
            
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
		
		
		
	}
}
