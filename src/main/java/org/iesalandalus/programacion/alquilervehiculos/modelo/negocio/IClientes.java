/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public interface IClientes {

	/**************************************************
	 * METODOS
	 **************************************************/
	List<Cliente> get();

	void insertar(Cliente cliente) throws OperationNotSupportedException;

	Cliente buscar(Cliente cliente);

	void borrar(Cliente cliente) throws OperationNotSupportedException;

	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;

	void comenzar();

	void terminar();

}