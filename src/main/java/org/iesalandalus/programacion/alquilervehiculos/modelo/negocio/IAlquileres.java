/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public interface IAlquileres {

	/***************************************************
	 * METODOS
	 ***************************************************/

	List<Alquiler> get();

	List<Alquiler> get(Cliente cliente);

	List<Alquiler> get(Vehiculo turismo);

	void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	Alquiler buscar(Alquiler alquiler);

	void borrar(Alquiler alquiler) throws OperationNotSupportedException;

	void comenzar();

	void terminar();

}