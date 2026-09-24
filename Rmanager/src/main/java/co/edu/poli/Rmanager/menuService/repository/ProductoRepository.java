package co.edu.poli.Rmanager.menuService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
