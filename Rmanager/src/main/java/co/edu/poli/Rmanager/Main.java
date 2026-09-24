package co.edu.poli.Rmanager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.Rmanager.menuService.model.entity.Categoria;
import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.service.ProductosService;
import co.edu.poli.Rmanager.usuarioService.service.EmpleadoService;

public class Main {
	
	//pruebas empleado
	
	@Autowired
	EmpleadoService empleadoService;
	
	//pruebas producto
	
	@Autowired
	ProductosService productoService; 
	
	ArrayList<Producto> pl = new ArrayList<Producto>();
	
	Categoria comidaRapida = new Categoria();
	
	Producto p1 = new Producto("Perro caliente", "Perro caliente tradicional", 15.0, comidaRapida, true);
	
	
	
	//pruebas detalle_producto
	

}
