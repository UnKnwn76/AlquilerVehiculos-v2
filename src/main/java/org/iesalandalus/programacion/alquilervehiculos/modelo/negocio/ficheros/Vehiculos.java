/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
	 
*************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

/*************************************************/

public class Vehiculos implements IVehiculos {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	private List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/

	private Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}

	/***************************************************
	 * METODOS
	 ***************************************************/

	static Vehiculos getInstancia() {
		if (instancia == null) {
			instancia = new Vehiculos();
		}
		return instancia;
	}
	
	@Override
	public List<Vehiculo> get() {
		return new ArrayList<>(coleccionVehiculos);
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		coleccionVehiculos.add(vehiculo);
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		Vehiculo vehiculoABusc = null;
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}

		int indice = coleccionVehiculos.indexOf(vehiculo);
		if (indice != -1) {
			vehiculoABusc = coleccionVehiculos.get(indice);
		}
		return vehiculoABusc;

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(vehiculo);
	}

}
