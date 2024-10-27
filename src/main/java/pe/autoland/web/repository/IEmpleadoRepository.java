package pe.autoland.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.autoland.web.model.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
