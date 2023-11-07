package consola;

import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

import logica.SistemaAlquiler;
import persistencia.Persistencia;
import logica.Cliente;
import logica.Empleado;
import logica.Sede;
import logica.Seguro;
import logica.Administrador;
import logica.AdministradorLocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class aplicacion {
    
    private static final Sede Null = null;

	private static Date parseFecha(String fechaStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws CsvValidationException, NumberFormatException {

        SistemaAlquiler sistema = new SistemaAlquiler();
        
        Administrador administrador = sistema.nuevoAdministrador("grupo9", "123");
        AdministradorLocal admiBogota=sistema.agregarAdmLocalBogota("admiBogota", "456",null);
        AdministradorLocal admiDorado=sistema.agregarAdmLocalDorado("admiDorado", "789",null);
        Sede sedeDorado=sistema.agregarSede("Aeropuerto El Dorado", "Avenida Calle 26 # 96A-21", admiDorado, null);
        Sede sedeBogota=sistema.agregarSede("Nuestro Bogota", "Avenida Carrera 86 # 55A-75", admiBogota, null);
        admiBogota.setSede(sedeBogota);
        admiDorado.setSede(sedeDorado);
        
        Persistencia persistencia = new Persistencia();
        persistencia.cargarDatos(sistema, "datos/vehiculos.csv", "datos/clientes.csv", "datos/empleados.csv", "datos/reservas.csv", "datos/seguros.csv"); 
        System.out.print("\nSE HAN CARGADO DATOS AUTOMATICAMENTE\n");
        
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nBienvenido/a al Sistema de Alquiler de Vehiculos!");
            System.out.println("1. Iniciar sesion como cliente");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Ingresar como administrador local");
            System.out.println("4. Iniciar sesion como empleado");
            System.out.println("5. Registrarse como cliente");
            System.out.println("6. Salir");
            System.out.println("Por favor seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.nextLine();

                System.out.print("Contrasena: ");
                String contrasena = scanner.nextLine();
                
                boolean verif = sistema.autenticarCliente(nombreUsuario, contrasena);
                
                if (verif) {
                    cCliente.ejecutarMenuCliente(nombreUsuario, sistema, scanner);
            } else {
                System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
            }
                
            } else if (opcion == 2) {
                boolean autenticado = false;
                while (!autenticado) {
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();

                    if (nombreUsuario.equals(administrador.getNombreUsuario()) && contrasena.equals(administrador.getContrasena())) {
                        System.out.println("\nInicio de sesión exitoso como administrador.");
						cAdmi.ejecutarMenuAdministrador(sistema);
                        autenticado = true;
                    } else {
                        System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                    }
                }
            } else if (opcion == 3) {
                System.out.print("Ingrese su nombre de usuario como administrador local: ");
                String nombreUsuario = scanner.nextLine();
                System.out.print("Ingrese su contraseña como administrador local: ");
                String contrasena = scanner.nextLine();

                List<AdministradorLocal> administradoresLocales = sistema.getAdministradoresLocales();
                AdministradorLocal admiActual = null;
                boolean autenticado = false;

                for (AdministradorLocal adminLocal : administradoresLocales) {
                    if (nombreUsuario.equals(adminLocal.getNombreUsuario()) && contrasena.equals(adminLocal.getContrasena())) {
                        admiActual = adminLocal;
                        autenticado = true;
                        System.out.println("\nInicio de sesión exitoso como administrador local.");
                    }
                }

                if (!autenticado) {
                    System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                }

                if (admiActual != null) {
                    cAdmiLocal.ejecutarMenuAdministradorLocal(sistema, scanner, admiActual);
                }
            } else if (opcion == 4) {
                boolean autenticado = false;               
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();
                    autenticado=sistema.autenticarEmpleado(nombreUsuario, contrasena);

					if (autenticado) {
                        System.out.println("\nInicio de sesión exitoso como empleado.");
                        cEmpleado.ejecutarMenuEmpleado(sistema, scanner);
                        autenticado = true;
                    } else {
                        System.out.println("\nCredenciales incorrectas. Intente nuevamente.");
                    }
                }
            else if (opcion == 5) {
            	
            	cCliente.crearCliente(sistema,scanner);

            } else if (opcion == 6) {
                System.out.println("Gracias por usar el Sistema de Alquiler de Vehiculos. Hasta luego!");
                salir = true; 
            }else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

}
