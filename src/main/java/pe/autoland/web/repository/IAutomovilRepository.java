package pe.autoland.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.autoland.web.model.Vehiculo;

public interface IAutomovilRepository extends JpaRepository<Vehiculo, Integer> {

}
