/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {

	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal <= values().length - 1;
	}

	public static TipoVehiculo get(int ordinal) {
		if (!(esOrdinalValido(ordinal))) {
			throw new ArrayIndexOutOfBoundsException("ERROR: El ordinal no es válido.");
		}
		return values()[ordinal];
	}

	public static TipoVehiculo get(Vehiculo vehiculo) {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El vehiculo no puede ser nulo.");
		}

		TipoVehiculo tipoVehiculo = null;

		if (vehiculo instanceof Turismo) {
			tipoVehiculo = TipoVehiculo.TURISMO;

		} else if (vehiculo instanceof Autobus) {
			tipoVehiculo = TipoVehiculo.AUTOBUS;

		} else if (vehiculo instanceof Furgoneta) {
			tipoVehiculo = TipoVehiculo.FURGONETA;

		}

		return tipoVehiculo;
	}

	@Override
	public String toString() {
		return String.format("%d - %s", ordinal(), nombre);
	}
}