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

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.EstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoInsertDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoPrintMetodoDePagoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateEstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateIncidenteDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateMesaDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateMetodoDePagoDTO;
import co.edu.poli.Rmanager.ordenService.service.PedidoService;

@RestController
@RequestMapping("api/ordenes")

public class OrdenController {
	
	
	@Autowired
	private PedidoService pedidoService;
	
	//crear
	@PreAuthorize("hasRole('admin')")
	@PostMapping("/{id}")
	public ResponseEntity<Pedido> create(@RequestBody PedidoInsertDTO pedido){
		
		Pedido saved = pedidoService.createPedido(pedido);
		
		return ResponseEntity.ok(saved);
	}
	
	//get all
	
	@PreAuthorize("hasRole('admin')")
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll(){
		
		return ResponseEntity.ok(pedidoService.getAllPedidos());
		
	}
	//get by id
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pedido>> getById(@PathVariable Long id){
		
		return ResponseEntity.ok(pedidoService.getPedidoById(id));
		
	}

	//delete
	
	@PreAuthorize("hasRole('admin')")
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
	
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/mesa")
	public ResponseEntity<String>updateMesa (PedidoUpdateMesaDTO mesa){
		
		boolean updatedMesa = pedidoService.updateMesaFromPedido(mesa);
		
		if(updatedMesa) {
			
			return ResponseEntity.ok("Mesa actualizada!");
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	//add incidente
	
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/incidente")
	public ResponseEntity<String>updateIncidente (@RequestBody PedidoUpdateIncidenteDTO incidente){
		
		boolean updateIncidente = pedidoService.addIncidenteToPedido(incidente);
		
		if(updateIncidente) {
			
			return ResponseEntity.ok("Incidente añadido!");
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
	}
	
	//mesas empleado
	
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/mesasEmpleado/{id}")
	public ResponseEntity<List<MesaActivaDTO>> getMesasByEmpleado(@PathVariable Long id){
		
		return ResponseEntity.ok(pedidoService.getMesasByEmpleado(id));
		
	}
	
	//mesas activas
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/mesasActivas")
	public ResponseEntity<List<MesaActivaDTO>> getMesasActivas(){
		
		return ResponseEntity.ok(pedidoService.getAllMesasActivas());
		
	}
	
	// get estado del pedido
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/estadoPedido/{id}")
	public ResponseEntity<EstadoDTO> getEstadoDelpedido(@PathVariable Long id){
		
		return ResponseEntity.ok(pedidoService.getEstadoOfPedido(id));
		
	}
	
	// get pedido by estado
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/pedido/estado")
	public ResponseEntity<List<Optional<Pedido>>> getEstadoByPedido(@RequestBody String estado){
		
		return ResponseEntity.ok(pedidoService.getPedidoByEstado(estado));
		
	}
	
	// get pedidos disponibles
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/pedido/pendientes")
	public ResponseEntity<List<Optional<Pedido>>> getPedidosPendientes(){
		
		return ResponseEntity.ok(pedidoService.getPedidosPendientes());
		
	}
	
	// update estado de pedido
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/estado")
	public ResponseEntity<EstadoDTO>updateEstadoPedido (@RequestBody PedidoUpdateEstadoDTO estado){
		
		return ResponseEntity.ok(pedidoService.updateEstadoOfPedido(estado)); 
				
	}
	
	// add productos to pedido
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/productos/add/{id}")
	public ResponseEntity<Pedido> addProductos (@PathVariable Long id ,@RequestBody List<Detalle_producto> productos){
		
		return ResponseEntity.ok(pedidoService.addProductosToPedido(productos, id)); 
				
	}
	
	// remove productos from pedido

	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/productos/remove/{id}")
	public ResponseEntity<Pedido> removeProductos (@PathVariable Long id ,@RequestBody List<Long> productos){
		
		return ResponseEntity.ok(pedidoService.removeProductosFromPedido(productos, id)); 
	}
	
	// update metodo de pago
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/pedido/metodoDePago")
	public ResponseEntity<PedidoPrintMetodoDePagoDTO> updateMetodoDePago (@RequestBody PedidoUpdateMetodoDePagoDTO pedido){
		
		return ResponseEntity.ok(pedidoService.updateMetodoDePago(pedido)); 
	}
	
}
