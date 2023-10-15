package logica;

import java.util.List;

public class Alquiler
{	
	private String id;
	private Vehiculo vehiculo;
	private double costoTotal;
	private boolean seguroAdicional;
    private List<ConductorAdicional> conductoresAdicionales;
	


    public Alquiler(String id, Vehiculo vehiculo, double costoTotal, boolean seguroAdicional,
		List<ConductorAdicional> conductoresAdicionales){
	this.id = id;
	this.vehiculo = vehiculo;
	this.costoTotal = costoTotal;
	this.seguroAdicional = seguroAdicional;
	this.conductoresAdicionales = conductoresAdicionales;
    }



	public String getId()
	{
		return id;
	}



	public void setId(String id)
	{
		this.id = id;
	}



	public Vehiculo getVehiculo()
	{
		return vehiculo;
	}



	public void setVehiculo(Vehiculo vehiculo)
	{
		this.vehiculo = vehiculo;
	}



	public double getCostoTotal()
	{
		return costoTotal;
	}



	public void setCostoTotal(double costoTotal)
	{
		this.costoTotal = costoTotal;
	}



	public boolean isSeguroAdicional()
	{
		return seguroAdicional;
	}



	public void setSeguroAdicional(boolean seguroAdicional)
	{
		this.seguroAdicional = seguroAdicional;
	}



	public List<ConductorAdicional> getConductoresAdicionales()
	{
		return conductoresAdicionales;
	}



	public void setConductoresAdicionales(List<ConductorAdicional> conductoresAdicionales)
	{
		this.conductoresAdicionales = conductoresAdicionales;
	}
    
    



}