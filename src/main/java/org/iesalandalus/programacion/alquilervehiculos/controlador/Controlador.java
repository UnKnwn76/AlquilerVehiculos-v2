/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/

package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class Controlador {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	private Vista vista;
	private Modelo modelo;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/

	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		this.modelo = modelo;

		if (vista == null) {
			throw new NullPointerException("ERROR: La vista no puede ser nula.");
		}
		this.vista = vista;
		vista.setControlador(this);
	}

	/***************************************************
	 * METODOS
	 ***************************************************/

	public void comenzar(){
		modelo.comenzar();
		vista.comenzar();
	}

	public void terminar() {
		modelo.terminar();
		vista.terminar();
	}


	/***************************************************
	 * METODOS DE INSERTAR
	 ***************************************************/

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		modelo.insertar(cliente);
	}

	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		modelo.insertar(vehiculo);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		modelo.insertar(alquiler);
	}

	/***************************************************
	 * METODOS DE BUSCAR
	 ***************************************************/

	public Cliente buscar(Cliente cliente) {
		return modelo.buscar(cliente);
	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		return modelo.buscar(vehiculo);

	}

	public Alquiler buscar(Alquiler alquiler) {
		return modelo.buscar(alquiler);

	}

	/***************************************************
	 * METODO DE MODIFICAR
	 ***************************************************/

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		modelo.modificar(cliente, nombre, telefono);
	}

	/***************************************************
	 * METODO DE DEVOLVER
	 ***************************************************/

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		modelo.devolver(cliente, fechaDevolucion);
	}
	
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		modelo.devolver(vehiculo, fechaDevolucion);
	}

	/***************************************************
	 * METODOS DE BORRAR
	 ***************************************************/

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		modelo.borrar(cliente);
	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		modelo.borrar(vehiculo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		modelo.borrar(alquiler);
	}

	/***************************************************
	 * METODOS DE LISTAR
	 ***************************************************/

	public List<Cliente> getClientes() {
		return modelo.getListaClientes();
	}

	public List<Vehiculo> getVehiculos() {
		return modelo.getListaVehiculos();

	}

	public List<Alquiler> getAlquileres() {
		return modelo.getListaAlquileres();

	}

	public List<Alquiler> getAlquileres(Cliente cliente) {
		return modelo.getListaAlquileres(cliente);
	}

	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
		return modelo.getListaAlquileres(vehiculo);
	}
}
