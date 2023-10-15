package logica;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Collections;

public class Alquiler
{	
	private int id;
	private Vehiculo vehiculo;
	private double costoTotal;
	private boolean seguroAdicional;
    private List<ConductorAdicional> conductoresAdicionales;
	


    public Alquiler(int id, Vehiculo vehiculo, double costoTotal, boolean seguroAdicional,
		List<ConductorAdicional> conductoresAdicionales){
	this.id = id;
	this.vehiculo = vehiculo;
	this.costoTotal = costoTotal;
	this.seguroAdicional = seguroAdicional;
	this.conductoresAdicionales = conductoresAdicionales;
    }



	public int getId()
	{
		return id;
	}



	public void setId(int id)
	{
		this.id = id;
	}



	public Vehiculo getVehiculo()
	{
		return vehiculo;
	}



	public void setVehiculo(Vehiculo vehiculo)
	{
		this.vehiculo = vehiculo;
	}



	public double getCostoTotal()
	{
		return costoTotal;
	}



	public void setCostoTotal(double costoTotal)
	{
		this.costoTotal = costoTotal;
	}



	public boolean isSeguroAdicional()
	{
		return seguroAdicional;
	}



	public void setSeguroAdicional(boolean seguroAdicional)
	{
		this.seguroAdicional = seguroAdicional;
	}



	public List<ConductorAdicional> getConductoresAdicionales()
	{
		return conductoresAdicionales;
	}



	public void setConductoresAdicionales(List<ConductorAdicional> conductoresAdicionales)
	{
		this.conductoresAdicionales = conductoresAdicionales;
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

            //lectura reserva
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
               	 	
               	 	
                    List<String> seguro= Seguro.escogerSeguro(scanner);
                    System.out.println("Desea agregar conductores adicionales? Escriba 1 para si, 0 para no: ");
                    String pregunta = scanner.nextLine();
                    int numeroAdicionales= 0;
                    if (pregunta.equals("1")) {
                    numeroAdicionales= ConductorAdicional.conductoresAgregados(scanner, usuario, id);}
                    else {
                    	System.out.println("Generando archivo de alquiler");
                    }
                    int valorTotal = ((Integer.parseInt(costo)-(int)Double.parseDouble(treinta))+(Integer.parseInt(seguro.get(1))*(int)Double.parseDouble(dias)));
                    
                    //crear el archivo del alquiler con todo lo recolectado
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
                    nuevaFila.add(Integer.toString(valorTotal));
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
    
    



}