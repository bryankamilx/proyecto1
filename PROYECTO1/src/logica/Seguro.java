package logica;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Seguro {
	
	private String nombre;
	private double precio;
	private String detalles;
	
	public Seguro(String nombre, double precio, String detalles) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.detalles = detalles;
	}
	
	

	public String getDetalles()
	{
		return detalles;
	}

	public void setDetalles(String detalles)
	{
		this.detalles = detalles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
