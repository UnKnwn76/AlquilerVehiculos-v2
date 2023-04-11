/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/

package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	/***************************************************
	 * CONSTRUCTOR
	 ***************************************************/
	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}

	/***************************************************
	 * METODOS DE INSERTAR
	 ***************************************************/

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(Vehiculo.copiar(vehiculo));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		getAlquileres().insertar(new Alquiler(getClientes().buscar(alquiler.getCliente()),
				getVehiculos().buscar(alquiler.getVehiculo()), alquiler.getFechaAlquiler()));
	}

	/***************************************************
	 * METODOS DE BUSCAR
	 ***************************************************/

	@Override
	public Cliente buscar(Cliente cliente) {
		return new Cliente(getClientes().buscar(cliente));
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		return Vehiculo.copiar(getVehiculos().buscar(vehiculo));
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	/***************************************************
	 * METODOS DE MODIFICAR
	 ***************************************************/

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);

	}

	/***************************************************
	 * METODOS DE DEVOLVER
	 ***************************************************/

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(cliente, fechaDevolucion);
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(vehiculo, fechaDevolucion);

	}

	/***************************************************
	 * METODOS DE BORRAR
	 ***************************************************/

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			getAlquileres().borrar(alquiler); // buscamos en la lista las qluileres de dicho cliente y las borramos
		}
		getClientes().borrar(cliente); // luego borramos el cliente

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			getAlquileres().borrar(alquiler); // buscamos en la lista las alquileres de dicho vehículo y las borramos
		}
		getVehiculos().borrar(vehiculo); // luego borramos el vehículo

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		getAlquileres().borrar(alquiler);

	}

	/***************************************************
	 * METODOS DE GET
	 ***************************************************/

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			listaClientes.add(new Cliente(cliente));
		}
		return listaClientes;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> listaVehiculos = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			listaVehiculos.add(Vehiculo.copiar(vehiculo));
		}
		return listaVehiculos;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			listaAlquileres.add(new Alquiler(alquiler));
		}
		return listaAlquileres;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		List<Alquiler> listaAlquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			listaAlquileresCliente.add(new Alquiler(alquiler));
		}
		return listaAlquileresCliente;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		List<Alquiler> listaAlquileresVehiculo = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			listaAlquileresVehiculo.add(new Alquiler(alquiler));
		}
		return listaAlquileresVehiculo;
	}

}
