package co.edu.poli.Rmanager.ordenService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;


@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
	
	//query que trae las mesas que no estan ocupadas 
	@Query("SELECT m FROM Mesa m WHERE m.ocupada = :disponibilidad")
	public List<Optional<Mesa>> findAllMesasDisponibles (@Param("disponibilidad") Boolean disponibilidad);
	

}
