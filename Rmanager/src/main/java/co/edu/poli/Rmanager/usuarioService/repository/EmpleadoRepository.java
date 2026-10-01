package co.edu.poli.Rmanager.usuarioService.repository;

import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	@Query("SELECT e FROM Empleado e WHERE e.cedula = :cedula")
	public Optional<Empleado> findByCedula(String cedula);
	
	
}
