package pe.autoland.web.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Integer idusuario;
	@Column(name = "contrasenia")
	private String contrasenia;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecharegistro")
	private Date fecharegistro;
	@Column(name = "mailusuario")
	private String mailusuario;
	@Column(name = "nomusuario")
	private String nomusuario;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="id_empleado")
	@PrimaryKeyJoinColumn(name = "id_empleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	@PrimaryKeyJoinColumn(name = "id_rol")
	private Rol rol;

	public Usuario() {
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Date getFecharegistro() {
		return this.fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getMailusuario() {
		return this.mailusuario;
	}

	public void setMailusuario(String mailusuario) {
		this.mailusuario = mailusuario;
	}

	public String getNomusuario() {
		return this.nomusuario;
	}

	public void setNomusuario(String nomusuario) {
		this.nomusuario = nomusuario;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}