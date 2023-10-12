package logica;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ObjectUtils;
import java.io.FileReader;

public class Sede {
    private String nombre;
    private String direccion;
    private String horarios;

    public Sede(String nombre, String direccion, String horarios) {
        this.nombre = nombre;
        this.direccion= direccion;
        this.horarios = horarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
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
