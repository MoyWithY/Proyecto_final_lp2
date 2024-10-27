package pe.autoland.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.autoland.web.model.Empleado;
import pe.autoland.web.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT u FROM Usuario u WHERE u.nomusuario = ?1 AND u.contrasenia = ?2")
	Usuario findByUserAndPassword(String nomusuario, String contrasenia);
	
	Usuario findByEmpleado(Empleado empleado);
}
