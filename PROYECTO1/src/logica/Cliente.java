 package logica;

import java.util.Date;

public class Cliente implements Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String numeroTelefonico;
    private String correo;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String imagenDocumento;
    private String numeroLicencia;
    private String paisExpedicionLicencia;
    private Date fechaVencimientoLicencia;
    private String datosTarjetaCredito;

    public Cliente(String nombreUsuario, String contrasena, String nombre, String numeroTelefonico,String correo,
                   Date fechaNacimiento, String nacionalidad, String imagenDocumento,
                   String numeroLicencia, String paisExpedicionLicencia,
                   Date fechaVencimientoLicencia, String datosTarjetaCredito) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.numeroTelefonico = numeroTelefonico;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.imagenDocumento = imagenDocumento;
        this.numeroLicencia = numeroLicencia;
        this.paisExpedicionLicencia = paisExpedicionLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.datosTarjetaCredito = datosTarjetaCredito;
    }

    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String getContrasena() {
        return contrasena;
    }

	public String getNombre()
	{
		return nombre;
	}

	public String getNumeroTelefonico()
	{
		return numeroTelefonico;
	}

	public String getCorreo()
	{
		return correo;
	}

	public Date getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	public String getNacionalidad()
	{
		return nacionalidad;
	}

	public String getImagenDocumento()
	{
		return imagenDocumento;
	}

	public String getNumeroLicencia()
	{
		return numeroLicencia;
	}

	public String getPaisExpedicionLicencia()
	{
		return paisExpedicionLicencia;
	}

	public Date getFechaVencimientoLicencia()
	{
		return fechaVencimientoLicencia;
	}

	public String getDatosTarjetaCredito()
	{
		return datosTarjetaCredito;
	}
    
    
    
}
