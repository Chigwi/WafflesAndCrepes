package co.edu.poli.Rmanager.ordenService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.ordenService.model.Entity.Historial_estado;


@Repository
public interface Historial_estadoRepository extends JpaRepository<Historial_estado, Long>{

}
