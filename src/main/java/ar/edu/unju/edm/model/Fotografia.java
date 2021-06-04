package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
@Entity
@Table (name="FOTOGRAFIAS")
@Component
public class Fotografia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer idFotos;
	@Column(name = "link_imagen", columnDefinition = "LONGBLOB")
	private String linkFoto;
	@ManyToOne
	@JoinColumn(name = "codigoPol",referencedColumnName="codigo")
	private Pol pol;
	public Fotografia() {
		// TODO Auto-generated constructor stub
	}
	public Fotografia(Integer idFotos, String linkFoto, Pol pol) {
		super();
		this.idFotos = idFotos;
		this.linkFoto = linkFoto;
		this.pol = pol;
	}
	public Integer getIdFotos() {
		return idFotos;
	}
	public void setIdFotos(Integer idFotos) {
		this.idFotos = idFotos;
	}
	public String getLinkFoto() {
		return linkFoto;
	}
	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
}
