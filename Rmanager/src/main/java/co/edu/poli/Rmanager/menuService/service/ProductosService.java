package co.edu.poli.Rmanager.menuService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.repository.DetalleProductoRepository;
import co.edu.poli.Rmanager.menuService.repository.ProductoRepository;

public class ProductosService {
	
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private DetalleProductoRepository detalleProductoRepository;
	
	
	//Producto
	
	//Insertar
	//@Transactional(transactionManager = "cancionTransactionManager")
	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto); 
	}
	
	
	//Get All
	public List<Producto> getAllProducto() {
		return productoRepository.findAll();
	}
	
	//Delete
	public boolean deleteProducto(Long id) {
	    if (productoRepository.existsById(id)) {
	        productoRepository.deleteById(id);
	        return true;
	    }
	    return false; 
	}
	
	//Traer uno por id
	public Optional<Producto> getProductoById(Long id) {
	    return productoRepository.findById(id);
	}
	
	//Update
	public Producto updateProducto(Producto newProducto) {
		return productoRepository.save(newProducto);
	}
	
	//DETALLE PRODUCTO
	
	//Insertar
	public Detalle_producto createCancion(Detalle_producto detalle_producto) {
		return detalleProductoRepository.save(detalle_producto); 
	}
	
	//Get All
		public List<Detalle_producto> getAllDetalleProducto() {
			return detalleProductoRepository.findAll();
		}
	
	//Delete
		public boolean deleteDetalle_producto(Long id) {
		    if (detalleProductoRepository.existsById(id)) {
		        detalleProductoRepository.deleteById(id);
		        return true;
		    }
		    return false; 
		}
		
		//Traer uno por id
		public Optional<Detalle_producto> getDetalleProductonById(Long id) {
		    return detalleProductoRepository.findById(id);
		}
		
		//Update
		public Detalle_producto updateDetalleProducto(Detalle_producto newDetalleProducto) {
			return detalleProductoRepository.save(newDetalleProducto);
		}
	
	

}
