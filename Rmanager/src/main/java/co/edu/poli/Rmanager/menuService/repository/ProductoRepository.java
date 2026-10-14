package co.edu.poli.Rmanager.menuService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("SELECT p FROM Pedido p WHERE p.es_menu_del_dia = disponible")
	public List<Optional<Producto>> findAllByMenuDelDia (Boolean disponible);
	
	

	

}
