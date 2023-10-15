package logica;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.util.UUID;


public class SistemaAlquiler {
    private List<Sede> sedes;
    private List<Vehiculo> inventario;
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    private List<Seguro> seguros;

    public SistemaAlquiler() {
    	List<Sede> sedes = new ArrayList<>();
    	List<Vehiculo> inventario = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        List<Seguro> seguros = new ArrayList<>();
    	
        this.sedes = sedes;
        this.inventario = inventario;
        this.clientes = clientes;
        this.reservas = reservas;
        this.seguros = seguros;
    }
    
    
    public AdministradorLocal agregarAdmLocalBogota(String nombreUsuario, String contrasena, Sede sede) {
    	AdministradorLocal administradorLocalNuestroBogota = new AdministradorLocal(nombreUsuario,contrasena,sede);
    	return administradorLocalNuestroBogota;
    }
    
    public AdministradorLocal agregarAdmLocalDorado(String nombreUsuario, String contrasena, Sede sede) {
    	AdministradorLocal administradorLocalDorado = new AdministradorLocal(nombreUsuario,contrasena,sede);
    	return administradorLocalDorado;
    }
    
    public Sede agregarSede(String nombre, String direccion, AdministradorLocal administradorLocal, List<Empleado> empleados) {
    	Sede sede = new Sede(nombre,direccion,administradorLocal,empleados);
    	sedes.add(sede);
		return sede;
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


    public Administrador nuevoAdministrador(String usuario,String contraseña) {
        Administrador administrador = new Administrador(usuario,contraseña);
		return administrador;
    }
    

    public void agregarVehiculo(String placa, String marca, String modelo, String color, String transmision, String categoria, 
    		String estado, String pasajeros, String tarifa) {
        Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, color, transmision, categoria, estado, pasajeros, tarifa);
        inventario.add(nuevoVehiculo);
    }
    
    public void eliminarAuto(String placaEliminar) {
        Iterator<Vehiculo> iterator = inventario.iterator();
        while (iterator.hasNext()) {
            Vehiculo vehiculo = iterator.next();
            if (vehiculo.getPlaca().equals(placaEliminar)) {
                iterator.remove(); 
                System.out.println("Vehículo con placa " + placaEliminar + " eliminado del inventario.");
            }
        }
    }


    public void realizarReserva(String nombre, Scanner scanner) {
    	
    	try (CSVReader reader = new CSVReader(new FileReader("datos/vehiculos.csv"))) {
    		
    		String[] linea;
            boolean primeraLinea = true;
            boolean bandera = false;
            int tiposDiferentes = 0;
            Set<String> tiposImpresos = new HashSet<>();
            List<List<String>> listaDeListas = new ArrayList<>();
            

            while ((linea = reader.readNext()) != null && tiposDiferentes < 16) {
                if (primeraLinea) {
                    primeraLinea = false; // Saltar la primera línea (encabezados)
                    continue;
                }
                List<String> lista = new ArrayList<>();
                
                String categoria = linea[5];
                
                if (!tiposImpresos.contains(categoria)) {
                String placa = linea[0];
                String marca = linea[1];
                String modelo = linea[2];
                String color = linea[3];
                String transmision = linea[4];
                String estado = linea[6];
                String numeroPasajeros = linea[7];
                String tarifaDiaria = linea[8]; 
                 

                Vehiculo carro = new Vehiculo(placa, marca, modelo, color, transmision,categoria, estado, 
                		numeroPasajeros, tarifaDiaria);
                tiposDiferentes++;
                
                System.out.println("Opcion " + String.valueOf(tiposDiferentes));
                System.out.println("Categoria: " + carro.getCategoria());
                lista.add(categoria);
                System.out.println("Tarifa Diaria: " + carro.getTarifa());
                lista.add(tarifaDiaria);
                System.out.println("Numero de Pasajeros: " + carro.getPasajeros());
                lista.add(numeroPasajeros);
                System.out.println();
                
                tiposImpresos.add(categoria);
                listaDeListas.add(lista);
                
                }
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
            List<String> sederec = Sede.seleccionarSede(scanner);
            System.out.println("En que sede le gustaria entregar el vehiculo?");
            System.out.println("(Entregarlo en una sede diferente a la cual lo recibio tiene un costo adicional)");
            List<String> sedeent = Sede.seleccionarSede(scanner);
            
            String idres = (UUID.randomUUID()).toString();
            
            Reserva.cargarReserva(idres,selec,nombre,sederec,sedeent,fechaHoraStr1,fechaHoraStr2,diferenciaEnDias);
            
            bandera= true;
            }
            
            
            
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

    }

    public void realizarAlquiler(Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                                  Date fechaRecogida, Date fechaEntrega, boolean seguroAdicional,
                                  List<ConductorAdicional> conductoresAdicionales) {
 
    }

    public void devolverVehiculo(Vehiculo vehiculo) {

    }

	public List<Vehiculo> getVehiculos() {
		return inventario;
	}
	
	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void agregarSeguro(String nombreSeguro, double precioSeguro) {
		Seguro nuevoSeguro = new Seguro(nombreSeguro, precioSeguro);
        seguros.add(nuevoSeguro);
	}
	
	public boolean eliminarSeguro(String nombreSeguro) {
	    for (Seguro seguro : seguros) {
	        if (seguro.getNombre().equalsIgnoreCase(nombreSeguro)) {
	            seguros.remove(seguro);
	            return true;
	        }
	    }
		return false;
	}



	public List<Sede> getSedes() {
	    return sedes;
	}

	
	public List<AdministradorLocal> getAdministradoresLocales() {
        List<AdministradorLocal> administradoresLocales = new ArrayList<>();
        for (Sede sede : sedes) {
            AdministradorLocal administradorLocal = sede.getAdministradorLocal();
            if (administradorLocal != null) {
                administradoresLocales.add(administradorLocal);
            }
        }
        return administradoresLocales;
    }



	public List<Empleado> getEmpleadosPorSede(Sede sede) {
        List<Empleado> empleadosPorSede = new ArrayList<>();
        for (Empleado empleado : sede.getEmpleados()) {
            if (empleado.getSede().getNombre().equals(sede.getNombre())) {
                empleadosPorSede.add(empleado);
            }
        }
        return empleadosPorSede;
    }



	public void crearEmpleado(String nombreUsuario, String contrasena, String nombre, String cargo, Sede sede) {
		
		Empleado nuevoEmpleado = new Empleado(nombreUsuario,contrasena,nombre,cargo,sede);
		sede.agregarEmpleado(nuevoEmpleado);
		
	}



	public void agregarReserva(String id, String categoria, String cliente, String sedeRecogida, String sedeEntrega,
			String fechaRecogida, String fechaEntrega, double costoParcial, double costoTreinta) {
		
		Reserva nuevaReserva = new Reserva(id, categoria, cliente, sedeRecogida, sedeEntrega, fechaRecogida, fechaEntrega, costoParcial,costoTreinta);
        reservas.add(nuevaReserva);
	}
	
	public void agregarCliente(String nombreUsuario, String contrasena, String nombre, String numeroTelefonico,String correo,
    		String fechaNacimiento, String nacionalidad,
            String numeroLicencia, String paisExpedicionLicencia,
            String fechaVencimientoLicencia, String datosTarjetaCredito) {
		
		Cliente cliente = new Cliente(nombreUsuario, contrasena, nombre, numeroTelefonico, correo, fechaNacimiento, nacionalidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, datosTarjetaCredito);
        clientes.add(cliente);
	}


	public List<Empleado> getEmpleados() {
        List<Empleado> todosLosEmpleados = new ArrayList<>();
        for (Sede sede : sedes) {
            todosLosEmpleados.addAll(sede.getEmpleados());
        }

        return todosLosEmpleados;
    }


	public List<Cliente> getClientes() {
	    List<Cliente> todosLosClientes = new ArrayList<>();

	    for (Cliente cliente : clientes) {
	        todosLosClientes.add(cliente);
	    }

	    return todosLosClientes;
	}


	public List<Reserva> getReservas() {
        return reservas;
    }

}
