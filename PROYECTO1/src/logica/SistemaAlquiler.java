package logica;

import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVWriter;
import java.text.SimpleDateFormat;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ObjectUtils;
import java.io.FileReader;


public class SistemaAlquiler {
    private List<Sede> sedes;
    private List<Vehiculo> inventario;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public SistemaAlquiler(List<Sede> sedes, List<Vehiculo> inventario, List<Cliente> clientes, List<Reserva> reservas) {
        this.sedes = sedes;
        this.inventario = inventario;
        this.clientes = clientes;
        this.reservas = reservas;
    }
    
    public SistemaAlquiler() {

    }
    
    
    public void agregarCliente(Cliente datos)
    {
    	 String rutaCompleta = "datos/clientes.csv";
    	 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    	 boolean archivoExiste = new File(rutaCompleta).exists();

         List<String> nuevaFila = new ArrayList<>();
         
         nuevaFila.add(datos.getNombreUsuario());
         nuevaFila.add(datos.getContrasena());
         nuevaFila.add(datos.getNombre());
         nuevaFila.add(datos.getNumeroTelefonico());
         nuevaFila.add(datos.getCorreo());
         nuevaFila.add(formatoFecha.format(datos.getFechaNacimiento()));
         nuevaFila.add(datos.getNacionalidad());
         nuevaFila.add(datos.getImagenDocumento());
         nuevaFila.add(datos.getNumeroLicencia());
         nuevaFila.add(datos.getPaisExpedicionLicencia());
         nuevaFila.add(formatoFecha.format(datos.getFechaVencimientoLicencia()));
         nuevaFila.add(datos.getDatosTarjetaCredito());
         

         try (CSVWriter writer = new CSVWriter(new FileWriter(rutaCompleta, true))) {
             if (archivoExiste==false) {
                 String[] encabezados = {"Nombre Usuario", "Contrasena", "Nombre Completo", "Numero Telefonico", "Correo",
                		 "Fecha De Nacimiento", "Nacionalidad", "Archivo Documentos", "Numero Licencia Conduccion", 
                		 "Pais Expedicion Licencia", "Fecha Vencimiento Licencia", "Datos Medio De Pago" };
                 writer.writeNext(encabezados);
             }
             writer.writeNext(nuevaFila.toArray(new String[0]));
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    public boolean autenticarCliente(String nombreUsuario, String contrasena) {
    	String usuarioIngresado = nombreUsuario;
        String contrasenaIngresada = contrasena;
        boolean bandera = false;

        try (CSVReader reader = new CSVReader(new FileReader("datos/clientes.csv"))) {
            
            int indiceUsuario = 0;
            int indiceContrasena = 1;

            String[] fila;
            while ((fila = reader.readNext()) != null) {
                String usuarioEnArchivo = fila[indiceUsuario];
                String contrasenaEnArchivo = fila[indiceContrasena];

                if (usuarioEnArchivo.equals(usuarioIngresado) && contrasenaEnArchivo.equals(contrasenaIngresada)) {
                    System.out.println("Autenticacion exitosa.");
                    bandera = true;
                    return bandera;
                    
                }
            }
            if (bandera==false) {	
            System.out.println("Nombre de usuario o contrasena incorrectos.");
            return bandera;}
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return bandera;
    }

    public void agregarSede(Sede sede) {
        sedes.add(sede);
    }

    public Administrador nuevoAdministrador(String usuario,String contraseña) {
        Administrador administrador = new Administrador(usuario,contraseña);
		return administrador;
    }

    public void agregarVehiculo(String placa, String marca, String modelo, String color, String transmision, String categoria, 
    		String estado, String pasajeros, String tarifa) {
        Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, color, transmision, categoria, estado, pasajeros, tarifa);
        //inventario.add(nuevoVehiculo);
    }


    public void realizarReserva(Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                                Date fechaRecogida, Date fechaEntrega) {

    }

    public void realizarAlquiler(Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                                  Date fechaRecogida, Date fechaEntrega, boolean seguroAdicional,
                                  List<ConductorAdicional> conductoresAdicionales) {
 
    }

    public void devolverVehiculo(Vehiculo vehiculo) {

    }
    
    

}
