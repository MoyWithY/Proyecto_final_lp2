package pe.autoland.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.autoland.web.model.Empleado;
import pe.autoland.web.model.Usuario;
import pe.autoland.web.repository.IUsuarioRepository;

@Service
public class UserService {
	
	@Autowired
	private IUsuarioRepository usuRepo;
	
	public Usuario validateUserByNomAndPassword(String nomusuario, String contrasenia) {
		Usuario u = usuRepo.findByUserAndPassword(nomusuario, contrasenia);
		return u;
	}
	
	public Usuario findByEmpleado(Empleado empleado) {
		Usuario u = usuRepo.findByEmpleado(empleado);
		return u;
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		return usuRepo.save(usuario);
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> listaRol = usuRepo.findAll();
		return listaRol;
	}
	
	public Usuario buscarUsuario(String id) {
		Usuario rol = usuRepo.getById(Integer.parseInt(id));
		return rol;
	}
	
	public void eliminarUsuario(String id) {
		this.usuRepo.deleteById(Integer.parseInt(id));
	}
}
