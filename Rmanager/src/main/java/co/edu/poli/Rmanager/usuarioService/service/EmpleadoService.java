package co.edu.poli.Rmanager.usuarioService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.model.entity.Rol;
import co.edu.poli.Rmanager.usuarioService.repository.EmpleadoRepository;
import co.edu.poli.Rmanager.usuarioService.repository.RolRepository;

@Service
@Primary
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	private final PasswordEncoder encoder;
	
	public EmpleadoService(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	public List<Empleado> getAllEmpleado(){
		return empleadoRepository.findAll();
	}
	
	//quitar despues
	
	public Empleado createEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}
	/*
	public Boolean getOrCreate(Empleado empleado) {
		
        Optional<Empleado> optionalPersona = empleadoRepository.findByIdEmpleado(empleado.getIdEmpleado());
	        if (!optionalPersona.isPresent()) {
	            empleado.setContrasenna(encoder.encode(empleado.getContrasenna()));

	            Optional<Rol> basic = rolRepository.findById(2L);
	            if (basic.isPresent()) {
	                empleado.setRol(basic.get());
	                Empleado e = empleadoRepository.save(empleado);
	                return true;
	            } else {
	                throw new RuntimeException("Rol básico no encontrado");
	            }
	        } else {
	            return false;
	        }
	    } */
	
	 public boolean deleteEmpleado(Long id) {
	        if (empleadoRepository.existsById(id)) {
	            empleadoRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	 public Optional<Empleado> getUsuarioById(Long id) {
	        return empleadoRepository.findById(id);
	    }
	
}
