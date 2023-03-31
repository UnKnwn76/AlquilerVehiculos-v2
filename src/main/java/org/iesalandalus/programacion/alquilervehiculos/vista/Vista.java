/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/

package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;

public abstract class Vista {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	private Controlador controlador;

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	protected Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controldor no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	
	public abstract void comenzar();

	public abstract void terminar();

}