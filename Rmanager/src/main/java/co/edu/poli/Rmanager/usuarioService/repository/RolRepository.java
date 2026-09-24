package co.edu.poli.Rmanager.usuarioService.repository;

import org.springframework.stereotype.Repository;

import co.edu.poli.Rmanager.usuarioService.model.entity.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;


@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
