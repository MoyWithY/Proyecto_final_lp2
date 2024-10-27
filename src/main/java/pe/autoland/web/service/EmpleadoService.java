package pe.autoland.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.autoland.web.model.Empleado;
import pe.autoland.web.model.Usuario;
import pe.autoland.web.repository.IEmpleadoRepository;

@Service
public class EmpleadoService {
	
	@Autowired
	private IEmpleadoRepository empRepo;
	@Autowired
	private UserService usuService;
	
	public Empleado guardarEmpleado(Empleado empleado) {
		return empRepo.save(empleado);
	}
	
	public List<Empleado> listarEmpleado() {
		List<Empleado> listaRol = empRepo.findAll();
		return listaRol;
	}
	
	public Empleado buscarEmpleado(String id) {
		Empleado rol = empRepo.getById(Integer.parseInt(id));
		return rol;
	}
	
	@Transactional
	public void eliminarEmpleado(String id) throws Exception{
		Usuario usu = new Usuario();
		usu = usuService.findByEmpleado(buscarEmpleado(id));
		if(usu == null) {
			this.empRepo.deleteById(Integer.parseInt(id));
		} else {
			String idUsu = String.valueOf(usu.getIdusuario());
			this.usuService.eliminarUsuario(idUsu);
			this.empRepo.deleteById(Integer.parseInt(id));
		}
	}
}
