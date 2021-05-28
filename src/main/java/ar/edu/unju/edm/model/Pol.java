package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

@Component
public class Pol {
	private String nombre;
	private String descripcion;
	private String etiqueta;
	private String sitioWeb;
	private int numeroCasa;
	private String barrio;
	private String localidad;
	private int localizacionLatitud;
	private int localizacionLongitud;
	public Pol() {
		// TODO Auto-generated constructor stub
	}
	public Pol(String nombre, String descripcion, String etiqueta, String sitioWeb, int numeroCasa, String barrio,
			String localidad, int localizacionLatitud, int localizacionLongitud) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.etiqueta = etiqueta;
		this.sitioWeb = sitioWeb;
		this.numeroCasa = numeroCasa;
		this.barrio = barrio;
		this.localidad = localidad;
		this.localizacionLatitud = localizacionLatitud;
		this.localizacionLongitud = localizacionLongitud;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	public int getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public int getLocalizacionLatitud() {
		return localizacionLatitud;
	}
	public void setLocalizacionLatitud(int localizacionLatitud) {
		this.localizacionLatitud = localizacionLatitud;
	}
	public int getLocalizacionLongitud() {
		return localizacionLongitud;
	}
	public void setLocalizacionLongitud(int localizacionLongitud) {
		this.localizacionLongitud = localizacionLongitud;
	}
	
}
