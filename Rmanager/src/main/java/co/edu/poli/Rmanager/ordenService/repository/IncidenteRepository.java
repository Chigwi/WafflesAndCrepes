package co.edu.poli.Rmanager.ordenService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;

@Repository
public interface IncidenteRepository  extends JpaRepository<Incidente, Long>{

}
