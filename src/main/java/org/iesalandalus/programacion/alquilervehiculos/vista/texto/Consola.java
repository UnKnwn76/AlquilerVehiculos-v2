/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd-MM-yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);
	private static final String PATRON_MES = "MM-yyyy";

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(); // para saltar linea
		System.out.println(mensaje);
		for (int i = 0; i < mensaje.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static void mostrarMenuAcciones() {
		System.out.println();
		mostrarCabecera("Bienvenido a nuesta aplicación, aquí tiene el menú de opciones");
		System.out.println(); // saltar de linea para crear más espacio en la consola.
		for (Accion opciones : Accion.values()) {
			System.out.println(opciones);

		}
		System.out.println(); // saltar de linea para crear más espacio en la consola.
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();
	}

	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Entrada.entero();

	}

	private static LocalDate leerFecha(String mensaje, String patron) {
		System.out.printf("%s (%s):", mensaje, patron);
		LocalDate fecha = null;
		String leerFecha = Entrada.cadena();
		try {
			if (patron.equals(PATRON_FECHA)) {
				fecha = LocalDate.parse(leerFecha, FORMATO_FECHA);
			} else if (patron.equals(PATRON_MES)) {
				fecha = LocalDate.parse("01-" + leerFecha, FORMATO_FECHA);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Fecha introducida no está válida.");
		}

		return fecha;
	}

	public static Accion elegirAccion() {
		Accion accionElegida = null;
		int opcion;
		do {
			opcion = leerEntero("Introduce una opcion válida: ");
			try {
				accionElegida = Accion.get(opcion);
			} catch (Exception e) {
				mostrarCabecera(e.getMessage());
			}

		} while (opcion < 0 || opcion >= Accion.values().length);

		return accionElegida;
	}

	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduce el DNI del cliente: "));
	}

	public static String leerNombre() {
		return leerCadena("Introduce el nombre del cliente: ");
	}

	public static String leerTelefono() {
		return leerCadena("Introduce el telefono del cliente : ");
	}

	public static Cliente leerCliente() throws OperationNotSupportedException {
		return new Cliente(leerNombre(), leerCadena("Introduce el DNI del cliente: "), leerTelefono());
	}

	public static void mostrarMenuTipoVehiculo() {
		mostrarCabecera("El menú de tipos de vehiculos: ");
		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			System.out.println(tipoVehiculo);
		}
	}

	public static TipoVehiculo elegirTipoVehiculo() {
		TipoVehiculo tipoVehiculo = null;
		int opcion;
		do {
			opcion = leerEntero("Elige el tipo de vehículo: ");
			try {
				tipoVehiculo = TipoVehiculo.get(opcion);
			} catch (Exception e) {
				mostrarCabecera(e.getMessage());
			}

		} while (opcion < 0 || opcion >= TipoVehiculo.values().length);

		return tipoVehiculo;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculoADevolver = null;
		String marca = leerCadena("Escribe la marca del vehículo: ");
		String modelo = leerCadena("Escribe el modelo del vehículo: ");
		String matricula = leerCadena("Escribe la matricula del vehículo: ");
		
		if (tipoVehiculo == TipoVehiculo.TURISMO) {
			vehiculoADevolver = new Turismo(marca, modelo, leerEntero("Escribe la cilindrada del turismo: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			vehiculoADevolver = new Autobus(marca, modelo, leerEntero("Escribe el número de plazas del autobús: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.FURGONETA) {
			vehiculoADevolver = new Furgoneta(marca, modelo, leerEntero("Escribe el PMA de la furgoneta: "),
					leerEntero("Escribe el número de plazas de la furgoneta: "), matricula);
		}
		return vehiculoADevolver;
	
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTipoVehiculo();
		return leerVehiculo(elegirTipoVehiculo());
	}

	public static Vehiculo leerVehiculoMatricula() {
		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduce la matricula del vehículo: "));
	}

	public static Alquiler leerAlquiler() {
		return new Alquiler(leerClienteDni(), leerVehiculoMatricula(),
				leerFecha("Escribe la fecha de alquiler: ", PATRON_FECHA));
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Escribe la fecha de devolución con esta forma", PATRON_FECHA);
	}

	public static LocalDate leerMes() {
		return leerFecha("Escribe un mes con esta forma", PATRON_MES);
	}
}