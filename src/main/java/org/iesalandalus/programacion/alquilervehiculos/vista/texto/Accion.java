/*************************************************
 Ismail Hilmi
 1ºDAW
 Tarea online 05
 *************************************************/
package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {


	SALIR("Salir") {
		@Override
		public void ejecutar() {
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar Cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarCliente();

		}
	},
	INSERTAR_VEHICULO("Insertar Vehículo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarVehiculo();

		}
	},
	INSERTAR_ALQUILER("Insertar Alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar Cliente") {
		@Override
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar Vehículo") {
		@Override
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar Alquiler") {
		@Override
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar Cliente.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver Alquiler de un Cliente.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerCliente();

		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver Alquiler de un Vehículo.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerVehiculo();

		}
	},
	BORRAR_CLIENTE("Borrar Cliente.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar Vehículo.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar Alquiler.") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarAlquiler();

		}
	},
	LISTAR_CLIENTES("Listar Clientes.") {
		@Override
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Listar Vehículos.") {
		@Override
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar Alquileres.") {
		@Override
		public void ejecutar() {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar Alquileres de un Cliente.") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar Alquileres de un Vehículo.") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresVehiculo();
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar Estadisticas Mensuales") {
		@Override
		public void ejecutar() {
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};

	private String texto;
	private static VistaTexto vista;

	/**************************************************
	 * CONSTRUCTORES
	 **************************************************/
	
	private Accion(String texto) {
		this.texto = texto;
	}

	/***************************************************
	 * METODOS
	 ***************************************************/
	
	static void setVista(VistaTexto vistaTx) {
		vista = vistaTx;
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal <= values().length - 1;
	}

	public static Accion get(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new ArrayIndexOutOfBoundsException("ERROR: Ordinal inválido");
		}
		return values()[ordinal];
	}
	
	public abstract void ejecutar() throws OperationNotSupportedException;

	/***************************************************
	 * toString
	 ***************************************************/
	
	@Override
	public String toString() {
		return ordinal() + " - " + texto;
	}

}
