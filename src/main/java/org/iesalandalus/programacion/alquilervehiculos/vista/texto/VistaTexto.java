/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public VistaTexto() {
		Accion.setVista(this);
	}

	@Override
	public void comenzar() {
		Accion accion;
		do {
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirAccion();

			try {
				accion.ejecutar();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (accion != Accion.SALIR);
	}

	@Override
	public void terminar() {
		System.out.println("Gracias por utilizar nuestra aplicación. ¡Hasta pronto!");
	}

	/***************************************************
	 * METODOS DE INSERTAR
	 ***************************************************/

	public void insertarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Insertar un Cliente|");
		try {
			Cliente cliente = Consola.leerCliente();
			getControlador().insertar(cliente);
			Consola.mostrarCabecera("Cliente insertado correctamente."); // si el cliente ha insertado correctamente nos
																			// devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	public void insertarVehiculo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Insertar Vehículo|");
		try {
			Vehiculo vehiculo = Consola.leerVehiculo();
			getControlador().insertar(vehiculo);
			Consola.mostrarCabecera("Vehículo insertado correctamente."); // si el turismo ha insertado correctamente
																			// nos
																			// devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	public void insertarAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Insertar Alquiler|");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			getControlador().insertar(alquiler);
			Consola.mostrarCabecera("Alquiler insertado correctamente."); // si el alquiler ha insertado correctamente
																			// // nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	/***************************************************
	 * METODOS DE BUSCAR
	 ***************************************************/

	public void buscarCliente() {
		Consola.mostrarCabecera("La opcion de |Buscar Cliente|");
		try {
			Cliente cliente = Consola.leerClienteDni();
			if (getControlador().buscar(cliente) == null) {
				Consola.mostrarCabecera("Cliente no encontrado");
			} else {
				Consola.mostrarCabecera("Cliente encontrado: " + getControlador().buscar(cliente));
			}
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	public void buscarVehiculo() {
		Consola.mostrarCabecera("La opcion de |Buscar Vehículo|");
		try {
			Vehiculo vehiculo = Consola.leerVehiculoMatricula();
			if (getControlador().buscar(vehiculo) == null) {
				Consola.mostrarCabecera("Vehículo no encontrado");
			} else {
				Consola.mostrarCabecera("Vehículo encontrado: " + getControlador().buscar(vehiculo));
			}

		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	public void buscarAlquiler() {
		Consola.mostrarCabecera("La opcion de |Buscar Alquiler|");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			if (getControlador().buscar(alquiler) == null) {
				Consola.mostrarCabecera("Alquiler no encontrado");
			} else {
				Consola.mostrarCabecera("Alquiler encontrado: " + getControlador().buscar(alquiler));
			}

		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	/***************************************************
	 * METODO DE MODIFICAR
	 ***************************************************/

	public void modificarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Modificar Cliente|");
		try {
			Cliente cliente = Consola.leerClienteDni();
			String nombre = Consola.leerNombre();
			String telefono = Consola.leerTelefono();
			getControlador().modificar(cliente, nombre, telefono);
			Consola.mostrarCabecera("La modificación ha sido correcta."); // si la modificacion ha sido correctamente
																			// nos devuelve este mensaje
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	/***************************************************
	 * METODOs DE DEVOLVER
	 ***************************************************/

	public void devolverAlquilerCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Devolver alquiler de Cliente|");
		try {
			Cliente cliente = Consola.leerClienteDni();
			LocalDate fechaDevolucion = Consola.leerFechaDevolucion();

			getControlador().devolver(cliente, fechaDevolucion);
			System.out.print("El alquiler del cliente ha sido devuelto correctamente");
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	public void devolverAlquilerVehiculo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Devolver alquiler de Vehículo|");
		try {
			Vehiculo vehiculo = Consola.leerVehiculoMatricula();
			LocalDate fechaDevolucion = Consola.leerFechaDevolucion();

			getControlador().devolver(vehiculo, fechaDevolucion);
			System.out.print("El alquiler del vehículo ha sido devuelto correctamente");
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}

	/***************************************************
	 * METODOS DE LISTAR
	 ***************************************************/

	public void listarClientes() {
		Consola.mostrarCabecera("La opcion de |Listar Clientes|");
		List<Cliente> listarClientes = getControlador().getClientes();
		if (!listarClientes.isEmpty()) {
			listarClientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
			// los clientes serán listados depende del nombre del cliente.
			// y si los nombres son iguales los ordenamos depende del DNI de cada cliente
			for (Cliente cliente : listarClientes) {
				System.out.println(cliente);
			}
		} else {
			System.out.print("No existe ningún cliente para listar.");
		}

	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("La opcion de |Listar Vehículos|");
		List<Vehiculo> listarVehiculos = getControlador().getVehiculos();
		if (!listarVehiculos.isEmpty()) {
			listarVehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
					.thenComparing(Vehiculo::getMatricula));
			// los vehículos serán listados depende de la marca del vehiculo
			// si las marcas son iguales, los ordenamos depende del modelo del vehiculo
			// y si los modelos son iguales los ordenamos depende del matricula de cada
			// vehiculo
			for (Vehiculo vehiculo : listarVehiculos) {
				System.out.println(vehiculo);
			}
		} else {
			System.out.print("No existe ningún vehículo para listar.");
		}

	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("La opcion de |Listar Alquileres|");
		List<Alquiler> listarAlquileres = getControlador().getAlquileres();
		if (!listarAlquileres.isEmpty()) {
			Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre)
					.thenComparing(Cliente::getDni);
			listarAlquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
					comparadorCliente));
			// los alquileres serán listados depende de la fecha de alqquiler
			// si las fechas de alquiler son iguales los ordenamos depende del nombre de
			// cliente
			// y si los nombres son iguales los ordenamos depende del DNI de cada cliente
			for (Alquiler alquiler : listarAlquileres) {
				System.out.println(alquiler);
			}
		} else {
			System.out.println("No existe ningún alquiler para listar.");
		}

	}

	public void listarAlquileresCliente() {
		Consola.mostrarCabecera("La opcion de |Listar Alquileres de un Cliente|");
		Cliente cliente = Consola.leerClienteDni();
		List<Alquiler> alquileresCliente = getControlador().getAlquileres(cliente);
		if (!alquileresCliente.isEmpty()) {
			alquileresCliente.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
			for (Alquiler alquiler : alquileresCliente) {
				System.out.println(alquiler);
			}

		} else {
			System.out.println("No existe ningún alquiler para listar para ese cliente.");
		}

	}

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("La opcion de |Listar Alquileres de un Vehículo|");
		Vehiculo vehiculo = Consola.leerVehiculoMatricula();
		List<Alquiler> alquileresVehiculo = getControlador().getAlquileres(vehiculo);
		if (!alquileresVehiculo.isEmpty()) {
			alquileresVehiculo.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
			for (Alquiler alquiler : alquileresVehiculo) {
				System.out.println(alquiler);
			}

		} else {
			System.out.print("No existe ningún alquiler para listar para ese vehículo.");
		}
	}

	/***************************************************
	 * METODOS DE BORRAR
	 ***************************************************/

	public void borrarCliente() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Borrar un Cliente|");
		try {
			Cliente cliente = Consola.leerClienteDni();
			getControlador().borrar(cliente);
			System.out.print("El cliente ha sido borrado correctamente.");
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	public void borrarVehiculo() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Borrar un Vehículo|");
		try {
			Vehiculo vehiculo = Consola.leerVehiculoMatricula();
			getControlador().borrar(vehiculo);
			System.out.print("El vehículo ha sido borrado correctamente.");
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	public void borrarAlquiler() throws OperationNotSupportedException {
		Consola.mostrarCabecera("La opcion de |Borrar un Alquiler|");
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			getControlador().borrar(alquiler);
			System.out.print("El alquiler ha sido borrado correctamente.");
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}

	}

	/***************************************************
	 * METODOS DE ESTADISTICAS
	 ***************************************************/

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		Consola.mostrarCabecera("La opcion de |Mostrar Estadisticas Mensuales|");
		Map<TipoVehiculo, Integer> mapaEstadisticas = new EnumMap<>(TipoVehiculo.class);

		for (TipoVehiculo tipoDeVehiculo : TipoVehiculo.values()) {
			mapaEstadisticas.put(tipoDeVehiculo, 0);
	    }

		return mapaEstadisticas;
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {

		Map<TipoVehiculo, Integer> mapaEstadisticas = inicializarEstadisticas();
		try {
			LocalDate mes = Consola.leerMes();
			List<Alquiler> alquileres = getControlador().getAlquileres();
				for (Alquiler alquiler : alquileres) {
					if (alquiler.getFechaAlquiler().getMonth().equals(mes.getMonth())
							&& alquiler.getFechaAlquiler().getYear() == mes.getYear()) {
						mapaEstadisticas.put(TipoVehiculo.get(alquiler.getVehiculo()), 
								mapaEstadisticas.get(TipoVehiculo.get(alquiler.getVehiculo()))+1);
					}
				}
				System.out.println(mapaEstadisticas);
		} catch (Exception e) {
			Consola.mostrarCabecera(e.getMessage());
		}
	}
}