package co.edu.poli.Rmanager.ordenService.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	//query que trae todos los pedidos con un cierto estado
	@Query("SELECT p FROM Pedido p WHERE p.estado.nombre = :estado")
	public List<Optional<Pedido>> findAllByEstado (String estado);
	

	/*@Query("SELECT p FROM Pedido p WHERE p.estado.nombre = disponible")
	public List<Optional<Pedido>> findAllByPedidosPendientes ();*/
	
	//query que trae todas las mesas de un empleado
	@Query("""
		    SELECT new co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO(
		        mesero.nombre, mesa.id, estado.nombre)
		    FROM Pedido p
		    JOIN p.mesero mesero
		    JOIN p.mesa mesa
		    JOIN p.estado estado
		    WHERE mesero.id_empleado = :idEmpleado
		      AND estado.nombre <> 'Entregado'
		    """)
	public List<MesaActivaDTO> findMesasActivaByEmpleado (@Param ("idEmpleado") Long idEmpleado);
	
	//query que trae todas las mesas actualmente activas y el empleado que las esta usando
	@Query("""  
			SELECT new co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO(
				mesero.nombre, mesa.id, estado.nombre)
			FROM Pedido p
			JOIN p.mesero mesero
			JOIN p.mesa mesa
			JOIN p.estado estado
			WHERE estado.nombre <> 'Entregado'
			""")
	public List<MesaActivaDTO> findAllMesasActivas();
	
	
}
