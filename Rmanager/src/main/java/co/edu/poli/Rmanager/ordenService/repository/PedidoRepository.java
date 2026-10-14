package co.edu.poli.Rmanager.ordenService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("SELECT p FROM Pedido p WHERE p.estado.nombre = estado")
	public List<Optional<Pedido>> findAllByEstado (String estado);

}
