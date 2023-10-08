package logica;

import java.util.Date;
import java.util.List;

public class SistemaAlquiler {
    private List<Sede> sedes;
    private List<Vehiculo> inventario;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public SistemaAlquiler(List<Sede> sedes, List<Vehiculo> inventario, List<Cliente> clientes, List<Reserva> reservas) {
        this.sedes = sedes;
        this.inventario = inventario;
        this.clientes = clientes;
        this.reservas = reservas;
    }

    public void agregarSede(Sede sede) {
        sedes.add(sede);
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        inventario.add(vehiculo);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void realizarReserva(Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                                Date fechaRecogida, Date fechaEntrega) {

    }

    public void realizarAlquiler(Cliente cliente, Vehiculo vehiculo, Sede sedeRecogida, Sede sedeEntrega,
                                  Date fechaRecogida, Date fechaEntrega, boolean seguroAdicional,
                                  List<ConductorAdicional> conductoresAdicionales) {
 
    }

    public void devolverVehiculo(Vehiculo vehiculo) {

    }

}
