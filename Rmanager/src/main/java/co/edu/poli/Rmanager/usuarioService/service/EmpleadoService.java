package co.edu.poli.Rmanager.usuarioService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.security.AppUserDetails;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.model.entity.Rol;
import co.edu.poli.Rmanager.usuarioService.repository.EmpleadoRepository;
import co.edu.poli.Rmanager.usuarioService.repository.RolRepository;

@Service
@Primary
public class EmpleadoService implements UserDetailsService{

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	private final PasswordEncoder encoder;
	
	public EmpleadoService(EmpleadoRepository empleadoRepository, RolRepository rolRepository, PasswordEncoder encoder) {
		this.empleadoRepository = empleadoRepository;
		this.rolRepository = rolRepository;
		this.encoder = encoder;
	}
	

	
	public List<Empleado> getAllEmpleado(){
		return empleadoRepository.findAll();
	}
	
	//quitar despues
	
	public Empleado createEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}
	
	public Boolean getOrCreate(Empleado empleado) {
		
        Optional<Empleado> optionalEmpleado = empleadoRepository.findByCedula(empleado.getCedula());
	        if (!optionalEmpleado.isPresent()) {
	            empleado.setContrasenna(encoder.encode(empleado.getContrasenna()));

	            Optional<Rol> basic = rolRepository.findById(empleado.getRol().getId_rol());
	            if (basic.isPresent()) {
	                empleado.setRol(basic.get());
	                empleadoRepository.save(empleado);
	                return true;
	            } else {
	                throw new RuntimeException("Rol básico no encontrado");
	            }
	        } else {
	            return false;
	        }
	    } 
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
	 
	 public Optional<Empleado> getUsuarioByUsername(String cedula)throws UsernameNotFoundException{
	    	Optional<Empleado> empleado = Optional.of(empleadoRepository.findByCedula(cedula)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + cedula)));

	    	return empleado;
	    }
	 
	 public UserDetails loadByCedula(String cedula) throws UsernameNotFoundException{
		 Empleado empleado = empleadoRepository.findByCedula(cedula)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + cedula));
		 List<GrantedAuthority> authorities = List.of(
		            new SimpleGrantedAuthority("ROLE_" + empleado.getRol().getNombre())
		        );
		 return new AppUserDetails(empleado.getId_empleado(), empleado.getCedula(), empleado.getContrasenna(), authorities);

	 }



	 @Override
	 public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
		return loadByCedula(cedula);
	 }



	 public boolean updateRol(Long id, Rol rol) {
			
			Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
			
			if(optionalEmpleado.isPresent()) {
				
				Empleado empleado = optionalEmpleado.get();
				
				empleado.setRol(rol);
				
				empleadoRepository.save(empleado);
				
				return true;
			}
			
			return false;
	 }


	
}
