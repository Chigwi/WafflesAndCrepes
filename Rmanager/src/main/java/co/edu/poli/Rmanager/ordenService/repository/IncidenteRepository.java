package co.edu.poli.Rmanager.ordenService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;

@Repository
public interface IncidenteRepository  extends JpaRepository<Incidente, Long>{

	
	@Query("SELECT i.descripcion FROM Incidente i WHERE i.pedido.id_pedido = :id")
	Optional<String> findDescripcionByPedidoId(@Param("id") Long id);
}
