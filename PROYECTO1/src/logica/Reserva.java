package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class Reserva {
    private String id;
    private String categoria;
    private String usuarioCliente;
    private String sedeRecogida;
    private String sedeEntrega;
    private String fechaRecogida;
    private String fechaEntrega;
    private String diasFacturados;
    private String costoParcial;
    private String costoTreinta;
    

    public Reserva(String id, String categoria, String usuarioCliente, String sedeRecogida, String sedeEntrega,
                   String fechaRecogida, String fechaEntrega,String diasFacturados, String costoParcial, String costoTreinta) {
        this.id = id;
        this.usuarioCliente = usuarioCliente;
        this.categoria = categoria;
        this.sedeRecogida = sedeRecogida;
        this.sedeEntrega = sedeEntrega;
        this.fechaRecogida = fechaRecogida;
        this.fechaEntrega = fechaEntrega;
        this.diasFacturados = diasFacturados;
        this.costoParcial = costoParcial;
        this.costoTreinta = costoTreinta;
    }

    public String getId() {
        return id;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
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

	public String getDiasFacturados()
	{
		return diasFacturados;
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
    	int valorsinext= Integer.parseInt(tarifa_diaria)* (int)dias;
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
    	nuevaFila.add(Double.toString(dias));
    	nuevaFila.add(Integer.toString(valorsinext));
    	nuevaFila.add(Double.toString(treinta));
    	
    	
    	
    	try (CSVWriter writer = new CSVWriter(new FileWriter(rutaCompleta, true))) {
            if (archivoExiste==false) {
                String[] encabezados = {"Id reserva", "Categoria escogida", "Usuario del cliente", "Sede de recogida", "Sede de entrega",
               		 "Fecha de inicio alquiler", "Fecha fin alquiler","Dias facturados", "Costo sin adicionales", " Treinta por ciento costo"};
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

	public String getCostoTreinta() {
		return costoTreinta;
	}

	public void setCostoTreinta(String costoTreinta) {
		this.costoTreinta = costoTreinta;
	}
}
