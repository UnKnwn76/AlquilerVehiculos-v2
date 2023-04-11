/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
*************************************************/

package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	private static final File FICHERO_ALQUILERES = new File(
			String.format("%s%s%s", "datos", File.separator, "alquileres.xml"));
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "cliente";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";

	private List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/

	private Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	/***************************************************
	 * METODOS
	 ***************************************************/

	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}
		return instancia;
	}

	@Override
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> alquileresCliente = new ArrayList<>();

		// usamos el for each para recorrer la lista de alquileres
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				// si existe algun alquiler para dicho cliente lo añademos
				// a nuesta lista con los alquileres de dicho cliente (alquileresCliente)
				alquileresCliente.add(alquiler);
			}
		}
		return alquileresCliente;
	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> alquileresVehiculo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquileresVehiculo.add(alquiler);
			}
		}
		return alquileresVehiculo;
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : get()) {
			if (alquiler.getCliente().equals(cliente)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			}
			if (alquiler.getVehiculo().equals(vehiculo)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
				if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
				}
			}
		}

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);

	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Iterator<Alquiler> iter = get(cliente).iterator();
		Alquiler alquilerAbierto = null;
		while (iter.hasNext() && alquilerAbierto == null) {
			Alquiler alquiler = iter.next();
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto = alquiler;
			}
		}
		return alquilerAbierto;
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Iterator<Alquiler> iter = get(vehiculo).iterator();
		Alquiler alquilerAbierto = null;
		while (iter.hasNext() && alquilerAbierto == null) {
			Alquiler alquiler = iter.next();
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto = alquiler;
			}
		}
		return alquilerAbierto;
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}
		Alquiler alquilerDev = getAlquilerAbierto(cliente);
		if (alquilerDev == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		alquilerDev.devolver(fechaDevolucion);

	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}
		Alquiler alquilerDev = getAlquilerAbierto(vehiculo);
		if (alquilerDev == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		alquilerDev.devolver(fechaDevolucion);

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		int posicionDeAlquiler = coleccionAlquileres.indexOf(alquiler);
		Alquiler alquilerBusc = null;
		if (posicionDeAlquiler != -1) {
			alquilerBusc = coleccionAlquileres.get(posicionDeAlquiler);
		}
		return alquilerBusc;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");

		}
		coleccionAlquileres.remove(alquiler);

	}

	/**************************************************
	 * METODOS AÑADIDOS
	 **************************************************/

	@Override
	public void comenzar() {
		Document documentoXml = UtilidadesXML.leerXmlDeFichero(FICHERO_ALQUILERES);
		if (documentoXml != null) {
			leerDom(documentoXml);
			System.out.println("El documento de alquileres ha sido leído correctamente.");
		} else {
			System.out.println("Error: El documento de alquileres no ha sido leído correctamente.");
		}
	}

	private void leerDom(Document documentoXml) {

		NodeList nodosAlquileres = documentoXml.getElementsByTagName(ALQUILER);
		for (int i = 0; i < nodosAlquileres.getLength(); i++) {
			Node alquiler = nodosAlquileres.item(i);
			if (alquiler.getNodeType() == Node.ELEMENT_NODE) {
				try {
					insertar(getAlquiler((Element) alquiler));
				} catch (Exception e) {
					System.out.println("Error al insertar de alquiler Nº " + i + "," + e.getMessage());
				}
			}
		}
	}

	private Alquiler getAlquiler(Element elemento) {

		String elementoCliente = elemento.getAttribute(CLIENTE);
		String elementoVehiculo = elemento.getAttribute(VEHICULO);

		LocalDate fechaAlquiler = LocalDate.parse(elemento.getAttribute(FECHA_ALQUILER), FORMATO_FECHA);

		String fechaDevolucionString = elemento.getAttribute(FECHA_DEVOLUCION);
		LocalDate fechaDevolucion = null;

		Cliente cliente = Clientes.getInstancia().buscar(Cliente.getClienteConDni(elementoCliente));
		Vehiculo vehiculo = Vehiculos.getInstancia().buscar(Vehiculo.getVehiculoConMatricula(elementoVehiculo));

		Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaAlquiler);
		if (!fechaDevolucionString.isEmpty()) {
			// si existe la fecha de devolución, devolvemos alquiler
			try {
				fechaDevolucion = LocalDate.parse(fechaDevolucionString, FORMATO_FECHA);
				alquiler.devolver(fechaDevolucion);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return alquiler;

	}

	@Override
	public void terminar() {
		UtilidadesXML.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);

	}

	private Document crearDom() {
		Document documentoXml = UtilidadesXML.crearConstructorDocumentoXml().newDocument();
		Element elementoAlquileres = documentoXml.createElement(RAIZ);
		documentoXml.appendChild(elementoAlquileres);

		for (Alquiler alquiler : coleccionAlquileres) {
			Element elementoAlquiler = getElemento(documentoXml, alquiler);
			documentoXml.getDocumentElement().appendChild(elementoAlquiler);
		}
		return documentoXml;
	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {

		Element elemento = documentoXml.createElement(ALQUILER);

		elemento.setAttribute(CLIENTE, alquiler.getCliente().getDni());
		elemento.setAttribute(VEHICULO, alquiler.getVehiculo().getMatricula());
		elemento.setAttribute(FECHA_ALQUILER, alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		if (alquiler.getFechaDevolucion() != null) {
			elemento.setAttribute(FECHA_DEVOLUCION, alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}
		return elemento;
	}

}
