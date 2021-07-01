package ar.edu.unju.edm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Pol implements Comparable<Pol>{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer codigo;
	@Column
	@NotBlank(message="El nombre del PoI es obligatorio")
	@Pattern(regexp="[A-Za-z' ']+",message="Solo se permiten letras")
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
	private double localizacionLatitud;
	@Column
	private double localizacionLongitud;
	@Column(columnDefinition = "LONGBLOB")
	private String fotoEnlace;
	@Column(columnDefinition = "LONGBLOB")
	private String fotoEnlace2;
	@Column(columnDefinition = "LONGBLOB")
	private String fotoEnlace3;
	@Column
	private int estrellas;
	@ManyToOne
	@JoinColumn(name = "correo",referencedColumnName="idTurista")
	private Turista turista;
	@OneToMany(mappedBy="pol",cascade=CascadeType.ALL)
	private List<Turista_Pols> turista_pols;
	public Pol() {
		// TODO Auto-generated constructor stub
	}
	
	public Pol(Integer codigo,
			@NotBlank(message = "El nombre del PoI es obligatorio") @Pattern(regexp = "[A-Za-z]+", message = "Solo se permiten letras") String nombre,
			@NotBlank(message = "La descripcion es obligatoria") String descripcion, String etiqueta, String sitioWeb,
			@NotNull @Min(value = 1, message = "Numero minimo 1") @Max(value = 10000, message = "Numero maximo 10000") int numeroCasa,
			@NotBlank(message = "El nombre del barrio es obligatorio") String barrio,
			@NotBlank(message = "El nombre de la localidad es obligatoria") String localidad,
			@NotBlank(message = "Campo obligatorio") String calle, int localizacionLatitud, int localizacionLongitud,
			String fotoEnlace, String fotoEnlace2, String fotoEnlace3) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.etiqueta = etiqueta;
		this.sitioWeb = sitioWeb;
		this.numeroCasa = numeroCasa;
		this.barrio = barrio;
		this.localidad = localidad;
		this.calle = calle;
		this.localizacionLatitud = localizacionLatitud;
		this.localizacionLongitud = localizacionLongitud;
		this.fotoEnlace = fotoEnlace;
		this.fotoEnlace2 = fotoEnlace2;
		this.fotoEnlace3 = fotoEnlace3;
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
	public double getLocalizacionLatitud() {
		return localizacionLatitud;
	}
	public void setLocalizacionLatitud(double d) {
		this.localizacionLatitud = d;
	}
	public double getLocalizacionLongitud() {
		return localizacionLongitud;
	}
	public void setLocalizacionLongitud(double d) {
		this.localizacionLongitud = d;
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
	public String getFotoEnlace() {
		return fotoEnlace;
	}
	public void setFotoEnlace(String fotoEnlace) {
		this.fotoEnlace = fotoEnlace;
	}
	public String getFotoEnlace2() {
		return fotoEnlace2;
	}
	public void setFotoEnlace2(String fotoEnlace2) {
		this.fotoEnlace2 = fotoEnlace2;
	}
	public String getFotoEnlace3() {
		return fotoEnlace3;
	}
	public void setFotoEnlace3(String fotoEnlace3) {
		this.fotoEnlace3 = fotoEnlace3;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	@Override
	public int compareTo(Pol o) {
		if (this.estrellas == o.getEstrellas()) {
            return 0;
        } else if (this.estrellas > o.getEstrellas()) {
            return 1;
        } else {
            return -1;
        }
	}
	
}