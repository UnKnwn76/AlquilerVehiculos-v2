/*************************************************
	 Ismail Hilmi
	 1ºDAW
	 Tarea online 05
*************************************************/
package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {

	/*************************************************
	 * ATRIBUTOS Y CONSTANTES
	 *************************************************/

	private static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+((\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*)";
	private static final String ER_DNI = "^\\d{8}[A-Z]";
	private static final String ER_TELEFONO = "^[679]\\d{8}";

	private String nombre;
	private String dni;
	private String telefono;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/

	public Cliente(String nombre, String dni, String telefono) {

		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Cliente(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		nombre = cliente.getNombre();
		dni = cliente.getDni();
		telefono = cliente.getTelefono();

	}

	/**************************************************
	 * GETTERS/SETTERS
	 **************************************************/
	
	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}

	/**************************************************
	 * METODOS
	 **************************************************/

	private static boolean comprobarLetraDni(String dni) {
		char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };

		// primero separamos los numeros de dni y la letra
		// guardamos cada uno en un variable nuevo

		String numerosDeDNI = dni.substring(0, 8);
		char letraDNI = dni.charAt(8);
		// el metodo charAt nos devuelva el caracter que esta en la position pasada como
		// parametro

		// utilizamos el metodo "Integer.parseInt" para convertir la cadena que tiene
		// los númreos del DNI en un número entero
		int numeroDni = Integer.parseInt(numerosDeDNI);

		char letraCorrecta = letras[numeroDni % 23];

		return letraDNI == letraCorrecta;
		// si la letra letraDNI es igual a letraCorrecta nos devuelva true,
		// sino false.
	}

	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Cliente", dni, "600000000");
	}


	/***************************************************
	 * hashCode, equals, toString
	 ***************************************************/
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono);
	}

}
