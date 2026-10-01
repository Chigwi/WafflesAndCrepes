package co.edu.poli.Rmanager.usuarioService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.service.EmpleadoService;

@RestController
@RequestMapping("api/empleado")
/*@EnableMethodSecurity*/
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@PostMapping("/crearempleado")
	public ResponseEntity<String> create(@RequestBody Empleado empleado){
		boolean r = empleadoService.getOrCreate(empleado);
		if(r) {
			return ResponseEntity.ok("Usuario creado exitosamente"); // This triggers JPA to insert into DB
		}else {
			return ResponseEntity.badRequest().body("Usuario ya existe en el sistema");
		}
	}
	
	@PreAuthorize("hasRol('admin')")
	@GetMapping
	public ResponseEntity<List<Empleado>> getAll(){
		return ResponseEntity.ok(empleadoService.getAllEmpleado());
	}
	
	@PreAuthorize("hasRol('admin')")
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Optional<Empleado>> getThisEmpleado(@PathVariable Long id){
		return ResponseEntity.ok(empleadoService.getUsuarioById(id));
	}
	
}
