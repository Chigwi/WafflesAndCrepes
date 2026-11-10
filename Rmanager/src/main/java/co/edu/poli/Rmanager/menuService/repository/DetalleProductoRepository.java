package co.edu.poli.Rmanager.menuService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.dto.ItemPedidoDTO;
import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;

@Repository

public interface DetalleProductoRepository extends JpaRepository<Detalle_producto, Long>{

	
	@Query("""
		    SELECT new co.edu.poli.Rmanager.ordenService.model.dto.ItemPedidoDTO(
		        d.id_detalle_producto, pr.id_producto, pr.nombre,
		        d.cantidad, d.precio_unitario,
		        d.precio_unitario * d.cantidad, d.notas)
		    FROM Detalle_producto d
		    JOIN d.producto pr
		    WHERE d.pedido.id_pedido = :idPedido
		    ORDER BY d.id_detalle_producto
		    """)
		List<ItemPedidoDTO> findItemsByPedido(@Param("idPedido") Long idPedido);
	
}
