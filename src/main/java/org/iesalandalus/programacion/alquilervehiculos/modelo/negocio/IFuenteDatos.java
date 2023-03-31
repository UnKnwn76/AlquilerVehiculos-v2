/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

public interface IFuenteDatos {

	IClientes crearClientes();

	IVehiculos crearVehiculos();

	IAlquileres crearAlquileres();

}