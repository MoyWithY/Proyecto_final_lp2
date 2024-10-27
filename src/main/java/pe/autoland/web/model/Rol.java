package pe.autoland.web.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto marca el campo como auto-incremental
    @Column(name = "idrol")
    private Integer idRol;

    @Column(name = "nomrol")
    private String nomRol;
    
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    public Rol() {}

    public Integer getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNomRol() {
        return this.nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }
    
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarios().add(usuario);
        usuario.setRol(this);
        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarios().remove(usuario);
        usuario.setRol(null);
        return usuario;
    }
}
