package pe.autoland.web.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name="vehiculo")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idvehiculo")
	private Integer idvehiculo;
	@Column(name = "nummatricula")
	private String nummatricula;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modelo")
	private String modelo;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechcompra")
	private Date fechcompra;
	@Column(name = "aniofabricacion")
	private int aniofabricacion;

	public Vehiculo() {
	}

	public Integer getIdvehiculo() {
		return this.idvehiculo;
	}

	public void setIdvehiculo(Integer idvehiculo) {
		this.idvehiculo = idvehiculo;
	}

	public int getAniofabricacion() {
		return this.aniofabricacion;
	}

	public void setAniofabricacion(int aniofabricacion) {
		this.aniofabricacion = aniofabricacion;
	}

	public Date getFechcompra() {
		return this.fechcompra;
	}

	public void setFechcompra(Date fechcompra) {
		this.fechcompra = fechcompra;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNummatricula() {
		return this.nummatricula;
	}

	public void setNummatricula(String nummatricula) {
		this.nummatricula = nummatricula;
	}

}