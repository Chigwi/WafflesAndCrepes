package co.edu.poli.Rmanager.menuService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;

@Repository

public interface DetalleProductoRepository extends JpaRepository<Detalle_producto, Long>{

}
