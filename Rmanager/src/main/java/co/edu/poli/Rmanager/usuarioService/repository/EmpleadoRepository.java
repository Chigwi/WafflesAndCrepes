package co.edu.poli.Rmanager.usuarioService.repository;

import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
}
