package co.edu.poli.Rmanager.ordenService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.service.PedidoService;

@RestController
@RequestMapping("api/ordenes")

public class OrdenController {
	
	
	@Autowired
	private PedidoService pedidoService;
	
	//crear
	@PreAuthorize("hasRol('admin')")
	@PostMapping("/{id}")
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido){
		
		Pedido saved = pedidoService.createPedido(pedido);
		
		return ResponseEntity.ok(saved);
	}
	
	//get all
	
	@PreAuthorize("hasRol('admin')")
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll(){
		
		return ResponseEntity.ok(pedidoService.getAllPedidos());
		
	}
	//get by id
	@PreAuthorize("hasRol('admin')")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pedido>> getById(@PathVariable Long id){
		
		return ResponseEntity.ok(pedidoService.getPedidoById(id));
		
	}

	//delete
	
	@PreAuthorize("hasRol('admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id){
		
		boolean deleted = pedidoService.deletePedido(id);
		
		if(deleted) {
			
			return ResponseEntity.noContent().build();
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	//update mesa
	
	@PreAuthorize("hasRol('admin')")
	@PutMapping("/mesa/{id}")
	public ResponseEntity<String>updateMesa (@PathVariable Long id, @RequestBody Mesa mesa){
		
		boolean updatedMesa = pedidoService.updateMesaFromPedido(id, mesa);
		
		if(updatedMesa) {
			
			return ResponseEntity.ok("Mesa actualizada!");
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	//add incidente
	
	@PreAuthorize("hasRol('admin')")
	@PutMapping("/incidente/{id}")
	public ResponseEntity<String>updateIncidente (@PathVariable Long id, @RequestBody String incidente){
		
		boolean updateIncidente = pedidoService.addIncidenteToPedido(id, incidente);
		
		if(updateIncidente) {
			
			return ResponseEntity.ok("Incidente añadido!");
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
	}
	
	@PreAuthorize("hasRol('admin')")
	@GetMapping("/mesasEmpleado/{id}")
	public ResponseEntity<List<MesaActivaDTO>> getMesasByEmpleado(@PathVariable Long id){
		
		return ResponseEntity.ok(pedidoService.getMesasByEmpleado(id));
		
	}
	
	@PreAuthorize("hasRol('admin')")
	@GetMapping("/mesasActivas")
	public ResponseEntity<List<MesaActivaDTO>> getMesasByEmpleado(){
		
		return ResponseEntity.ok(pedidoService.getAllMesasActivas());
		
	}
	
	
	
}
