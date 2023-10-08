package logica;

import java.util.Date;
import java.util.List;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Sede sedeRecogida;
    private Sede sedeEntrega;
    private Date fechaRecogida;
    private Date fechaEntrega;
    private double costoTotal;
    private boolean seguroAdicional;
    private List<ConductorAdicional> conductoresAdicionales;

    public Reserva(int id, Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                   Date fechaRecogida, Date fechaEntrega, double costoTotal, boolean seguroAdicional,
                   List<ConductorAdicional> conductoresAdicionales) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.sedeRecogida = sedeRecogida;
        this.sedeEntrega = sedeEntrega;
        this.fechaRecogida = fechaRecogida;
        this.fechaEntrega = fechaEntrega;
        this.costoTotal = costoTotal;
        this.seguroAdicional = seguroAdicional;
        this.conductoresAdicionales = conductoresAdicionales;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Sede getSedeRecogida() {
        return sedeRecogida;
    }

    public Sede getSedeEntrega() {
        return sedeEntrega;
    }

    public Date getFechaRecogida() {
        return fechaRecogida;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public boolean isSeguroAdicional() {
        return seguroAdicional;
    }

    public List<ConductorAdicional> getConductoresAdicionales() {
        return conductoresAdicionales;
    }
}
