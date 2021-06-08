package ar.edu.unju.edm.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
@Entity
@Table (name="POLS")
@Component
public class Pol {
	//pruebadea
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer codigo;
	@Column
	@NotBlank(message="El nombre del PoI es obligatorio")
	@Pattern(regexp="[A-Za-z]+",message="Solo se permiten letras")
	private String nombre;
	@Column
	@NotBlank(message="La descripcion es obligatoria")
	private String descripcion;
	@Column
	private String etiqueta;
	@Column
	private String sitioWeb;
	@Column
	@NotNull
	@Min(value = 1,message="Numero minimo 1")
    @Max(value= 10000,message="Numero maximo 10000")
	private int numeroCasa;
	@Column
	@NotBlank(message="El nombre del barrio es obligatorio")
	private String barrio;
	@Column
	@NotBlank(message="El nombre de la localidad es obligatoria")
	private String localidad;
	@Column
	@NotBlank(message="Campo obligatorio")
	private String calle;
	@Column
	private int localizacionLatitud;
	@Column
	private int localizacionLongitud;
	@Column(columnDefinition = "LONGBLOB")
	private String fotoEnlace;
	private List<Fotografia> foto;
	
	@ManyToOne
	@JoinColumn(name="Turista", referencedColumnName="idTurista")
	private Turista turista1;
	@ManyToOne
	@JoinColumn(name = "eMail")
	private Turista turista;
	@ManyToOne
	@JoinColumn(name = "codpol")
	private Pol pol;
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
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public List<Fotografia> getFoto() {
		return foto;
	}
	public void setFoto(List<Fotografia> foto) {
		this.foto = foto;
	}
	public String getFotoEnlace() {
		return fotoEnlace;
	}
	public void setFotoEnlace(String fotoEnlace) {
		this.fotoEnlace = fotoEnlace;
	}
	
}