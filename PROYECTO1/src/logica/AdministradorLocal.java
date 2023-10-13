package logica;

public class AdministradorLocal implements Usuario{
	
	private String nombreUsuario;
    private String contrasena;
    private Sede sede;

	@Override
	public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String getContrasena() {
        return contrasena;
    }
	
    public Sede getSede() {
		return sede;
	}
}
