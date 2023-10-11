package logica;

import java.util.Date;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String transmision;
    private String categoria;
    private String estado;
    private String pasajeros;
    private String tarifa;

    public Vehiculo(String placa, String marca, String modelo, String color, String transmision, String categoria, String estado, String pasajeros,
    		String tarifa) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.transmision = transmision;
        this.categoria = categoria;
        this.estado = estado;
        this.pasajeros = pasajeros;
        this.tarifa = tarifa;
        
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPasajeros()
	{
		return pasajeros;
	}
    
    public String getTarifa()
	{
		return tarifa;
	}
    public void setPasajeros(String pasajeros)
	{
		this.pasajeros = pasajeros;
	}

	public void setTarifa(String tarifa)
	{
		this.tarifa = tarifa;
	}
    
    
    // METODOS

	public void reservar(Date fechaRecogida, Date fechaEntrega) {
        if (estado.equals("DISPONIBLE")) {
            estado = "RESERVADO";
        }
    }

    public void alquilar() {
        if (estado.equals("RESERVADO")) {
            estado = "ALQUILADO";
        }
    }

    public void devolver() {
        if (estado.equals("ALQUILADO")) {
            estado = "DISPONIBLE";
        }
    }

    public void realizarMantenimiento() {
        estado = "EN_MANTENIMIENTO";

    }

    public void finalizarMantenimiento() {
        estado = "DISPONIBLE";
    }

	
}
	
	

