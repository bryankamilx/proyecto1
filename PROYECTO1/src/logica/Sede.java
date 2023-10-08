package logica;

public class Sede {
    private String nombre;
    private String horarios;

    public Sede(String nombre, String horarios) {
        this.nombre = nombre;
        this.horarios = horarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

}
