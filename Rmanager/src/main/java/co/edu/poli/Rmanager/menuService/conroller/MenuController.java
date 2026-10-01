package co.edu.poli.Rmanager.menuService.conroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.service.ProductosService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/menu")

public class MenuController {
	
	@Autowired
	private ProductosService productoService;
	
	//PRODUCTO
	
		//create
		@PreAuthorize("hasRol('admin')")
		@PostMapping
		public ResponseEntity<Producto> create(@RequestBody Producto producto){
		Producto saved = productoService.createProducto(producto);
		return ResponseEntity.ok(saved);
		}
	

		//getAll
		@PreAuthorize("hasRol('admin')")
		@GetMapping
		public ResponseEntity<List<Producto>> getAll(){
			return ResponseEntity.ok(productoService.getAllProducto());
		}
		
		//getById
		@PreAuthorize("hasRol('admin')")
		@GetMapping("/{id}")
		public ResponseEntity<Optional<Producto>> getById(@PathVariable Long id){
			return ResponseEntity.ok(productoService.getProductoById(id));
			
		}
		
		//Delete
		@PreAuthorize("hasRol('admin')")
		@DeleteMapping("/{id}")
		public ResponseEntity<String> delete (@PathVariable Long id){
			boolean deleted = productoService.deleteProducto(id);
			if (deleted) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build(); 
			}
		
		}
		
	//DETALLE PRODUCTO
		
				//create
				@PreAuthorize("hasRol('admin')")
				@PostMapping ("/detalleProducto")
				public ResponseEntity<Detalle_producto> create(@RequestBody Detalle_producto detalle_producto){
				Detalle_producto saved = productoService.createDetalleProducto(detalle_producto);
				return ResponseEntity.ok(saved);
				}
				//getAll
				@PreAuthorize("hasRol('admin')")
				@GetMapping("/detalleProducto")
				public ResponseEntity<List<Detalle_producto>> getAllDetalles(){
					return ResponseEntity.ok(productoService.getAllDetalleProducto());
				}
				
				//getById
				@PreAuthorize("hasRol('admin')")
				@GetMapping("/detalleProducto/{id}")
				public ResponseEntity<Optional<Detalle_producto>> getByIdDetalleProducto(@PathVariable Long id){
					return ResponseEntity.ok(productoService.getDetalleProductonById(id));
					
				}
				
				//Delete
				@PreAuthorize("hasRol('admin')")
				@DeleteMapping("/detalleProducto/{id}")
				public ResponseEntity<String> deleteDetalleProducto (@PathVariable Long id){
					boolean deleted = productoService.deleteDetalle_producto(id);
					if (deleted) {
						return ResponseEntity.noContent().build();
					} else {
						return ResponseEntity.notFound().build(); 
					}
				
				}
				
				
			
		
		

	
}
