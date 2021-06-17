package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;
@Entity
@Component
@Table(name = "Turistas",uniqueConstraints=
@UniqueConstraint(columnNames = {"email"}))
public class Turista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idTurista;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String paisPro;
	@Column
	private int puntos;
	@Column
	private double latitud;
	@Column
	private double longitud;
	
	public Turista(Integer idTurista, String email, String password, String nombre, String apellido, String paisPro,
			int puntos, double latitud, double longitud) {
		super();
		this.idTurista = idTurista;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.paisPro = paisPro;
		this.puntos = puntos;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Turista() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdTurista() {
		return idTurista;
	}
	public void setIdTurista(Integer idTurista) {
		this.idTurista = idTurista;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPaisPro() {
		return paisPro;
	}
	public void setPaisPro(String paisPro) {
		this.paisPro = paisPro;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public Turista(Integer idTurista, String email, String password, String nombre, String apellido, String paisPro,
			int puntos) {
		super();
		this.idTurista = idTurista;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.paisPro = paisPro;
		this.puntos = puntos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idTurista == null) ? 0 : idTurista.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((paisPro == null) ? 0 : paisPro.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + puntos;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turista other = (Turista) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idTurista == null) {
			if (other.idTurista != null)
				return false;
		} else if (!idTurista.equals(other.idTurista))
			return false;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (paisPro == null) {
			if (other.paisPro != null)
				return false;
		} else if (!paisPro.equals(other.paisPro))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (puntos != other.puntos)
			return false;
		return true;
	}
	
	

}
