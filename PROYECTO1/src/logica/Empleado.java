package logica;

public class Empleado implements Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String cargo;

    public Empleado(String nombreUsuario, String contrasena, String nombre, String cargo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }
}