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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
@Entity
@Table(name="TURISTA_POLS")
@Component
public class Turista_Pols {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer idTurista_Pols;
	@ManyToOne
	@JoinColumn(name = "idTurista",referencedColumnName="idTurista")
	private Turista turista;
	@ManyToOne
	@JoinColumn(name = "idPol")
	private Pol pol;
	@Column
	private int valoracion;
	@Column
	private String comentario;
	public Turista_Pols() {
		// TODO Auto-generated constructor stub
	}
	public Turista_Pols(Integer idTurista_Pols, Turista turista, Pol pol, int valoracion, String comentario) {
		super();
		this.idTurista_Pols = idTurista_Pols;
		this.turista = turista;
		this.pol = pol;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}
	public Integer getIdTurista_Pols() {
		return idTurista_Pols;
	}
	public void setIdTurista_Pols(Integer idTurista_Pols) {
		this.idTurista_Pols = idTurista_Pols;
	}
	public Turista getTurista() {
		return turista;
	}
	public void setTurista(Turista turista) {
		this.turista = turista;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getValoracionGeneral(List<Turista_Pols> lista,Integer a) {
		int cont=0,total=0,general;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getValoracion()!=0) {
				if(lista.get(i).getPol().getCodigo()==a) {
					total=total+lista.get(i).getValoracion();
					cont++;
				}
			}
		}
		if(cont==0) {
			cont=1;
		}
		general=total/cont;
		return general;
	}
	public int getVerificarValoracion(List<Turista_Pols> lista,Integer a,Integer b) {
		int total=0;
		boolean band=true;
		for(int i=0;i<lista.size() && band==true;i++) {
				if(lista.get(i).getPol().getCodigo()==a) {
					if(lista.get(i).getTurista().getIdTurista()==b) {
						if(lista.get(i).getValoracion()>0 ) {
							band=false;
							total=1;
						}
					}
				}
		}
		return total;
	}
	public int getVotosPols(List<Turista_Pols> lista,Integer a) {
		int cont=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getValoracion()!=0) {
				if(lista.get(i).getPol().getCodigo()==a) {
					cont++;
				}
			}
		}
		return cont;
	}
}
