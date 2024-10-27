package pe.autoland.web.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name="empleado")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idempleado")
	private Integer idempleado;
	@Column(name = "apeempleado")
	private String apeempleado;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "dni")
	private String dni;
	@Column(name = "nomempleado")
	private String nomempleado;
	@Column(name = "telempleado")
	private String telempleado;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="empleado")
	private List<Usuario> usuarios;
	
	public Empleado() {
	}

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public String getApeempleado() {
		return this.apeempleado;
	}

	public void setApeempleado(String apeempleado) {
		this.apeempleado = apeempleado;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNomempleado() {
		return this.nomempleado;
	}

	public void setNomempleado(String nomempleado) {
		this.nomempleado = nomempleado;
	}

	public String getTelempleado() {
		return this.telempleado;
	}

	public void setTelempleado(String telempleado) {
		this.telempleado = telempleado;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEmpleado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEmpleado(null);

		return usuario;
	}
}