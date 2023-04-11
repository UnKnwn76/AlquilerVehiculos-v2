/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
	 
*************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*************************************************/

public class Vehiculos implements IVehiculos {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/
	private static final File FICHERO_VEHICULOS = new File(
			String.format("%s%s%s", "datos", File.separator, "vehiculos.xml"));
	private static final String RAIZ = "vehiculos";
	private static final String VEHICULO = "vehiculo";
	private static final String MARCA = "marca";
	private static final String MODELO = "modelo";
	private static final String MATRICULA = "matricula";
	private static final String CILINDRADA = "cilindrada";
	private static final String PLAZAS = "plazas";
	private static final String PMA = "pma";
	private static final String TIPO = "tipo";
	private static final String TURISMO = "turismo";
	private static final String AUTOBUS = "autobus";
	private static final String FURGONETA = "furgoneta";

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

	/**************************************************
	 * METODOS AÑADIDOS
	 **************************************************/

	@Override
	public void comenzar() {
		Document documentoXml = UtilidadesXML.leerXmlDeFichero(FICHERO_VEHICULOS);
		if (documentoXml != null) {
			leerDom(documentoXml);
			System.out.println("El documento de vehículos ha sido leído correctamente.");
		} else {
			System.out.println("Error: El documento de vehículos no ha sido leído correctamente.");
		}

	}

	private void leerDom(Document documentoXml) {
		NodeList nodosVehiculos = documentoXml.getElementsByTagName(VEHICULO);
		for (int i = 0; i < nodosVehiculos.getLength(); i++) {
			Node cliente = nodosVehiculos.item(i);
			if (cliente.getNodeType() == Node.ELEMENT_NODE) {
				try {
					insertar(getVehiculo((Element) cliente));
				} catch (Exception e) {
					System.out.println("Error al insertar de vehículo: Nº " + i + ", " + e.getMessage());
				}
			}

		}
	}

	private Vehiculo getVehiculo(Element elemento) {
		String tipoDeVehiculo = elemento.getAttribute(TIPO);
		String marca = elemento.getAttribute(MARCA);
		String modelo = elemento.getAttribute(MODELO);
		String matricula = elemento.getAttribute(MATRICULA);

		Vehiculo vehiculo = null;

		if (tipoDeVehiculo.equals(TURISMO)) {
			int cilindrada = Integer.parseInt(elemento.getAttribute(CILINDRADA));
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);

		} else if (tipoDeVehiculo.equals(AUTOBUS)) {
			int plazas = Integer.parseInt(elemento.getAttribute(PLAZAS));
			vehiculo = new Autobus(marca, modelo, plazas, matricula);

		} else if (tipoDeVehiculo.equals(FURGONETA)) {
			int plazas = Integer.parseInt(elemento.getAttribute(PLAZAS));
			int pma = Integer.parseInt(elemento.getAttribute(PMA));
			vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
		} else {
			System.out.println("Error: El tipo de vehículo no es correcto.");
		}
		return vehiculo;

	}

	@Override
	public void terminar() {
		UtilidadesXML.escribirXmlAFichero(crearDom(), FICHERO_VEHICULOS);
	}

	private Document crearDom() {
		Document documentoXml = UtilidadesXML.crearConstructorDocumentoXml().newDocument();
		Element elementoClientes = documentoXml.createElement(RAIZ);
		documentoXml.appendChild(elementoClientes);
		for (Vehiculo vehiculo : coleccionVehiculos) {
			Element elementoVehiculo = getElemento(documentoXml, vehiculo);
			documentoXml.getDocumentElement().appendChild(elementoVehiculo);
		}

		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Vehiculo vehiculo) {

		Element elementoVehiculo = documentoXml.createElement(VEHICULO);

		if (vehiculo instanceof Turismo turismo) {
			elementoVehiculo.setAttribute(TIPO, TURISMO);
			elementoVehiculo.setAttribute(CILINDRADA, String.valueOf(turismo.getCilindrada()));

		} else if (vehiculo instanceof Autobus autobus) {
			elementoVehiculo.setAttribute(TIPO, AUTOBUS);
			elementoVehiculo.setAttribute(PLAZAS, String.valueOf(autobus.getPlazas()));

		} else if (vehiculo instanceof Furgoneta furgoneta) {
			elementoVehiculo.setAttribute(TIPO, FURGONETA);
			elementoVehiculo.setAttribute(PLAZAS, String.valueOf(furgoneta.getPlazas()));
			elementoVehiculo.setAttribute(PMA, String.valueOf(furgoneta.getPma()));
		}

		elementoVehiculo.setAttribute(MARCA, vehiculo.getMarca());
		elementoVehiculo.setAttribute(MODELO, vehiculo.getModelo());
		elementoVehiculo.setAttribute(MATRICULA, vehiculo.getMatricula());

		return elementoVehiculo;
	}

}
