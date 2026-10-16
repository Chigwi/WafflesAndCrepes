package co.edu.poli.Rmanager.ordenService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
