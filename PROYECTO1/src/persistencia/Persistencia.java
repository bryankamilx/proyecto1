package persistencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;

import logica.Cliente;
import logica.Empleado;
import logica.Reserva;
import logica.Sede;
import logica.Seguro;
import logica.SistemaAlquiler;
import logica.Vehiculo;

public class Persistencia {
	
	public void cargarDatos(SistemaAlquiler sistema,String rutaVehiculos, String rutaClientes, String rutaEmpleados, String rutaReservas, String rutaSeguros) throws CsvValidationException, NumberFormatException {
        
		leerVehiculos(sistema,rutaVehiculos);
        leerClientes(sistema,rutaClientes);
        leerEmpleados(sistema,rutaEmpleados);
        leerReservas(sistema,rutaReservas);
        leerSeguros(sistema,rutaSeguros);

}

	public static void leerSeguros(SistemaAlquiler sistema, String rutaSeguros) throws CsvValidationException, NumberFormatException {
	    try {
	        CSVReader csvReader = new CSVReaderBuilder(new FileReader(rutaSeguros))
	            .withSkipLines(1)
	            .build();
	        String[] linea;
	        while ((linea = csvReader.readNext()) != null) {
	            String nombre = linea[0];
	            double precio = Double.parseDouble(linea[1]);
	            sistema.agregarSeguro(nombre, precio);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void leerReservas(SistemaAlquiler sistema, String rutaReservas) throws CsvValidationException {
	    try {
	        CSVReader csvReader = new CSVReaderBuilder(new FileReader(rutaReservas))
	            .withSkipLines(1)
	            .build();
	        String[] linea;
	        while ((linea = csvReader.readNext()) != null) {
	            String id = linea[0];
	            String categoria = linea[1];
	            String usuarioCliente = linea[2];
	            String sedeRecogida = linea[3];
	            String sedeEntrega = linea[4];
	            String fechaRecogida = linea[5];
	            String fechaEntrega = linea[6];
	            double costoParcial = Double.parseDouble(linea[7]);
	            double costoTreinta = Double.parseDouble(linea[7]);
	            sistema.agregarReserva(id, categoria, usuarioCliente, sedeRecogida, sedeEntrega, fechaRecogida, fechaEntrega, costoParcial,costoTreinta);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void leerEmpleados(SistemaAlquiler sistema, String rutaEmpleados) {
	    try {
	        CSVReader csvReader = new CSVReaderBuilder(new FileReader(rutaEmpleados))
	            .withSkipLines(1)
	            .build();
	        String[] linea;

	        while ((linea = csvReader.readNext()) != null) {
	            String nombreUsuario = linea[0];
	            String contrasena = linea[1];
	            String nombreCompleto = linea[2];
	            String cargo = linea[3];
	            String sedeNombre = linea[4];
	            
	            Sede sede = null;
	            for (Sede s : sistema.getSedes()) {
	                if (s.getNombre().equals(sedeNombre)) {
	                    sede = s;
	                    break;
	                }
	            }

	            if (sede != null) {
	                Empleado empleado = new Empleado(nombreUsuario, contrasena, nombreCompleto, cargo, sede);
	                sede.agregarEmpleado(empleado);
	            } else {
	                System.out.println("No se encontró la sede correspondiente para el empleado: " + nombreUsuario);
	            }
	        }
	    } catch (IOException | CsvValidationException e) {
	        e.printStackTrace();
	    }
	}

	public static void leerClientes(SistemaAlquiler sistema, String rutaClientes) throws CsvValidationException {
	    try {
	        CSVReader csvReader = new CSVReaderBuilder(new FileReader(rutaClientes))
	            .withSkipLines(1)
	            .build();
	        String[] linea;
	        while ((linea = csvReader.readNext()) != null) {
	            String nombreUsuario = linea[0];
	            String contrasena = linea[1];
	            String nombre = linea[2];
	            String numeroTelefonico = linea[3];
	            String correo = linea[4];
	            String fechaNacimiento = linea[5];
	            String nacionalidad = linea[6];
	            String numeroLicencia = linea[7];
	            String paisExpedicionLicencia = linea[8];
	            String fechaVencimientoLicencia = linea[9];
	            String datosTarjetaCredito = linea[10];
	            sistema.agregarCliente(nombreUsuario, contrasena, nombre, numeroTelefonico, correo, fechaNacimiento, nacionalidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, datosTarjetaCredito);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void leerVehiculos(SistemaAlquiler sistema, String rutaVehiculos) {
        try {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(rutaVehiculos))
                .withSkipLines(1)
                .build();
            String[] linea;
            while ((linea = csvReader.readNext()) != null) {
                String placa = linea[0];
                String marca = linea[1];
                String modelo = linea[2];
                String color = linea[3];
                String transmision = linea[4];
                String categoria = linea[5];
                String estado = linea[6];
                String cantidadPasajeros = linea[7];
                String tarifaDiaria = linea[8];
                sistema.agregarVehiculo(placa, marca, modelo, color, transmision, categoria, estado, cantidadPasajeros, tarifaDiaria);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


	public static void escribirSeguros(SistemaAlquiler sistema, String rutaSeguros) {
        try {
            FileWriter fileWriter = new FileWriter(rutaSeguros);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] header = {"Nombre", "Precio"};
            csvWriter.writeNext(header);

            List<Seguro> seguros = sistema.getSeguros(); 

            for (Seguro seguro : seguros) {
                String nombre = seguro.getNombre();
                double precio = seguro.getPrecio();

                String[] data = {nombre, Double.toString(precio)};
                csvWriter.writeNext(data);
            }

            csvWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void escribirReservas(SistemaAlquiler sistema, String rutaReservas) {
	        try {

	            FileWriter fileWriter = new FileWriter(rutaReservas);


	            CSVWriter csvWriter = new CSVWriter(fileWriter);


	            String[] header = {
	                "ID", "Categoria", "UsuarioCliente", "SedeRecogida", "SedeEntrega",
	                "FechaRecogida", "FechaEntrega", "CostoParcial", "CostoTreinta"
	            };

	            csvWriter.writeNext(header);


	            for (Reserva reserva : sistema.getReservas()) {
	                String[] data = {
	                    reserva.getId(),
	                    reserva.getCategoria(),
	                    reserva.getUsuarioCliente(),
	                    reserva.getSedeRecogida(),
	                    reserva.getSedeEntrega(),
	                    reserva.getFechaRecogida(),
	                    reserva.getFechaEntrega(),
	                    String.valueOf(reserva.getCostoParcial()),
	                    String.valueOf(reserva.getCostoTreinta())
	                };

	                csvWriter.writeNext(data);
	            }
	            csvWriter.close();
	            fileWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static void escribirClientes(SistemaAlquiler sistema, String rutaClientes) {
	    try {

	        FileWriter fileWriter = new FileWriter(rutaClientes);

	        CSVWriter csvWriter = new CSVWriter(fileWriter);

	        String[] header = {
	            "NombreUsuario", "Contrasena", "Nombre", "NumeroTelefonico", "Correo",
	            "FechaNacimiento", "Nacionalidad", "NumeroLicencia", "PaisExpedicionLicencia",
	            "FechaVencimientoLicencia", "DatosTarjetaCredito"
	        };
	        csvWriter.writeNext(header);

	        for (Cliente cliente : sistema.getClientes()) {
	            String[] data = {
	                cliente.getNombreUsuario(),
	                cliente.getContrasena(),
	                cliente.getNombre(),
	                cliente.getNumeroTelefonico(),
	                cliente.getCorreo(),
	                cliente.getFechaNacimiento(),
	                cliente.getNacionalidad(),
	                cliente.getNumeroLicencia(),
	                cliente.getPaisExpedicionLicencia(),
	                cliente.getFechaVencimientoLicencia(),
	                cliente.getDatosTarjetaCredito()
	            };
	            csvWriter.writeNext(data);
	        }

	        csvWriter.close();
	        fileWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void escribirEmpleados(SistemaAlquiler sistema, String rutaEmpleados) {
    try {

        FileWriter fileWriter = new FileWriter(rutaEmpleados);


        CSVWriter csvWriter = new CSVWriter(fileWriter);


        String[] header = {"NombreUsuario", "Contrasena", "Nombre", "Cargo", "SedeNombre"};
        csvWriter.writeNext(header);

        for (Empleado empleado : sistema.getEmpleados()) {
            String[] data = {
                empleado.getNombreUsuario(),
                empleado.getContrasena(),
                empleado.getNombre(),
                empleado.getCargo(),
                (empleado.getSede() != null) ? empleado.getSede().getNombre() : "" 
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

	public static void escribirVehiculos(SistemaAlquiler sistema, String rutaVehiculos) {
		try {

	        FileWriter fileWriter = new FileWriter(rutaVehiculos);


	        CSVWriter csvWriter = new CSVWriter(fileWriter);


	        String[] header = {"Placa", "Marca", "Modelo", "Color", "Transmisión", "Categoría", "Estado", "Pasajeros", "Tarifa"};
	        csvWriter.writeNext(header);


	        for (Vehiculo vehiculo : sistema.getVehiculos()) {
	            String[] data = {
	                vehiculo.getPlaca(),
	                vehiculo.getMarca(),
	                vehiculo.getModelo(),
	                vehiculo.getColor(),
	                vehiculo.getTransmision(),
	                vehiculo.getCategoria(),
	                vehiculo.getEstado(),
	                vehiculo.getPasajeros(),
	                vehiculo.getTarifa()
	            };
	            csvWriter.writeNext(data);
	        }


	        csvWriter.close();
	        fileWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	 public static void escribirEventosVehiculos(SistemaAlquiler sistema, String rutaEventos) {
	        try {
	            FileWriter fileWriter = new FileWriter(rutaEventos);
	            CSVWriter csvWriter = new CSVWriter(fileWriter);

	            String[] header = {"placa", "evento"};
	            csvWriter.writeNext(header);
	            Map<String, List<String>> eventos = sistema.getEventosVehiculos();

	            for (Map.Entry<String, List<String>> entry : eventos.entrySet()) {
	                String placa = entry.getKey();
	                List<String> eventosPlaca = entry.getValue();
	                for (String evento : eventosPlaca) {
	                    String[] data = {placa, evento};
	                    csvWriter.writeNext(data);
	                }
	            }

	            csvWriter.close();
	            fileWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}
