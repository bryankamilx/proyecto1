package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;

public class Reserva {
    private String id;
    private String categoria;
    private String cliente;
    private String sedeRecogida;
    private String sedeEntrega;
    private String fechaRecogida;
    private String fechaEntrega;
    private String costoParcial;

    public Reserva(String id, String categoria, String cliente, String sedeRecogida, String sedeEntrega,
                   String fechaRecogida, String fechaEntrega, String costoParcial) {
        this.id = id;
        this.cliente = cliente;
        this.categoria = categoria;
        this.sedeRecogida = sedeRecogida;
        this.sedeEntrega = sedeEntrega;
        this.fechaRecogida = fechaRecogida;
        this.fechaEntrega = fechaEntrega;
        this.costoParcial = costoParcial;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getSedeRecogida() {
        return sedeRecogida;
    }

    public String getSedeEntrega() {
        return sedeEntrega;
    }

    public String getFechaRecogida() {
        return fechaRecogida;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

	public String getCategoria()
	{
		return categoria;
	}

	public String getCostoParcial()
	{
		return costoParcial;
	}
    
    public static void cargarReserva(String id, List<String> datos, String cliente, List<String> recogida, List<String> entregada, 
    		String inicio, String fin, double dias) {
    	String rutaCompleta = "datos/reservas.csv";
    	boolean archivoExiste = new File(rutaCompleta).exists();
        List<String> nuevaFila = new ArrayList<>();
    	
        String tarifa_diaria=datos.get(1);
    	int valorsinext= Integer.parseInt(tarifa_diaria)* ((int)dias);
    	double treinta = valorsinext * 0.30;
    	String categoria = datos.get(0);
    	String rec = recogida.get(0);
    	String ent = entregada.get(0);
    	
    	nuevaFila.add(id);
    	nuevaFila.add(categoria);
    	nuevaFila.add(cliente);
    	nuevaFila.add(rec);
    	nuevaFila.add(ent);
    	nuevaFila.add(inicio);
    	nuevaFila.add(fin);
    	nuevaFila.add(Integer.toString(valorsinext));
    	nuevaFila.add(Double.toString(treinta));
    	
    	
    	
    	try (CSVWriter writer = new CSVWriter(new FileWriter(rutaCompleta, true))) {
            if (archivoExiste==false) {
                String[] encabezados = {"Id reserva", "Categoria escogida", "Usuario del cliente", "Sede de recogida", "Sede de entrega",
               		 "Fecha de inicio alquiler", "Fecha fin alquiler", "Costo sin adicionales", " Treinta por ciento costo"};
                writer.writeNext(encabezados);
            }
            writer.writeNext(nuevaFila.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	System.out.println("Su reserva fue creada exitosamente");
    	System.out.println("El valor total sin adicionales de su reserva es de: " + Integer.toString(valorsinext) );
    	System.out.println("Se cargo el treinta por ciento de ese valor (" + Double.toString(treinta) + ") a su medio de pago");
    	
    	
    	
    }
}
