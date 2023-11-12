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
import logica.Empleado;
import logica.SistemaAlquiler;
import logica.Vehiculo;
import persistencia.Persistencia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelEmpleado
{
	private String usuario;
    private String contrasena;

    public PanelEmpleado() {
        // Crear un nuevo JFrame
        JFrame frame = new JFrame("Iniciar sesión como empleado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        // Etiquetas y campos de texto
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField();
        
     // Botón de inicio de sesión
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar credenciales
                if (verificarCredenciales(usuarioField.getText(), new String(contrasenaField.getPassword()))) {
                    // Abrir nueva ventana si las credenciales son correctas
                    abrirVentanaBienvenida();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
     // Añadir elementos al JFrame
        frame.add(usuarioLabel);
        frame.add(usuarioField);
        frame.add(contrasenaLabel);
        frame.add(contrasenaField);
        frame.add(botonAceptar);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
 // Método para verificar credenciales en el archivo CSV
    private boolean verificarCredenciales(String usuario, String contrasena) {
        List<Empleado> empleados = leerEmpleadosDesdeCSV("./datos/empleados.csv");

        for (Empleado emp : empleados) {
            if (emp.getNombreUsuario().equals(usuario) && emp.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return true;
    }

    // Método para leer empleados desde un archivo CSV
    private List<Empleado> leerEmpleadosDesdeCSV(String archivo) {
        List<Empleado> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String usuario = datos[0].trim();
                String contrasena = datos[1].trim();

                empleados.add(new Empleado(usuario, contrasena, contrasena, contrasena, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    // Método para abrir la nueva ventana de bienvenida
    private void abrirVentanaBienvenida() {
        JFrame frameBienvenida = new JFrame("Iniciar sesión como empleado");
        frameBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameBienvenida.setSize(400, 300);

        // Crear elementos de la nueva ventana
        JLabel etiquetaBienvenida = new JLabel("BIENVENIDO EMPLEADO");
        JLabel etiquetaPregunta = new JLabel("¿Qué desea hacer?");
        JButton boton1 = new JButton("Completar alquiler de reserva");
        JButton boton2 = new JButton("Actualizar estado de nu vehículo");
        JButton boton3 = new JButton("Reportar mantenimiento de vehículo y fecha de disponibilidad");
        JButton boton4 = new JButton("Salir del menú");

        // Añadir elementos a la nueva ventana
        frameBienvenida.setLayout(new GridLayout(7, 1));
        frameBienvenida.add(etiquetaBienvenida);
        frameBienvenida.add(etiquetaPregunta);
        frameBienvenida.add(boton1);
        frameBienvenida.add(boton2);
        frameBienvenida.add(boton3);
        frameBienvenida.add(boton4);

        

        // Configurar ActionListener para el botón "Completar alquiler de reserva"
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAlquilerReserva();
            }
        });

        // Hacer visible la nueva ventana
        frameBienvenida.setVisible(true);
    }

    // Método para abrir la ventana de "Completar alquiler de reserva"
    private void abrirVentanaAlquilerReserva() {
        JFrame frameAlquilerReserva = new JFrame("Iniciar sesión como empleado");
        frameAlquilerReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAlquilerReserva.setSize(400, 200);

        // Crear elementos de la nueva ventana
        JLabel etiquetaTitulo = new JLabel("COMPLETAR ALQUILER DE LA RESERVA");
        JLabel etiquetaID = new JLabel("Ingrese ID de la reserva: ");
        JTextField campoID = new JTextField();
        JButton botonAceptarAlquiler = new JButton("Aceptar");

        // Añadir elementos a la nueva ventana
        frameAlquilerReserva.setLayout(new GridLayout(4, 2, 10, 10));
        frameAlquilerReserva.add(etiquetaTitulo);
        frameAlquilerReserva.add(new JLabel());  // Espacio en blanco
        frameAlquilerReserva.add(etiquetaID);
        frameAlquilerReserva.add(campoID);
        frameAlquilerReserva.add(new JLabel());  // Espacio en blanco
        frameAlquilerReserva.add(new JLabel());  // Espacio en blanco
        frameAlquilerReserva.add(botonAceptarAlquiler);

        // Configurar ActionListener para el botón "Aceptar"
        botonAceptarAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID de la reserva ingresado
                String idReserva = campoID.getText();

                // Verificar el ID de la reserva en el archivo CSV de reservas
                if (verificarIDReserva(idReserva, "./datos/reservas.csv")) {
                    JOptionPane.showMessageDialog(null, "ID de reserva válido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "ID de reserva no válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Hacer visible la nueva ventana
        frameAlquilerReserva.setVisible(true);
    }

    // Método para verificar el ID de la reserva en el archivo CSV de reservas
    private boolean verificarIDReserva(String idReserva, String archivoReservas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Supongamos que el archivo de reservas tiene un formato simple de una línea por reserva
                if (linea.trim().equals(idReserva)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PanelEmpleado();
            }
        });
    }
    
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
                String estado = Persistencia.buscarDisponible(placa);
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
                		Persistencia.cambiarEstado(placa, estatus);
                		
                		System.out.println("\n");
                		System.out.println("\n");
                		
                		System.out.println("Ahora el vehículo se encuentra en estado de Disponible.");
                		sistema.agregarEventoAlHistorial(placa, "Se actualizo el estado del vechiculo con placa " + placa + " a disponible");
                		Persistencia.escribirEventosVehiculos(sistema,"datos/eventos.csv");
                	}
                	
                	
                
	                else if (respuesta == 2) 
	                {
	                	
	                	String estatus = "Alquilado";
	                	Persistencia.cambiarEstado(placa, estatus);
                		
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
	        		
	        		Persistencia.cambiarMantenimiento(placa, estado, observacion, fecha);
	        		
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
                    
                    Persistencia.cambiarMantenimiento(placa, estatus, observacion, fecha);
                    
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
