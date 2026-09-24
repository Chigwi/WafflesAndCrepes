package co.edu.poli.Rmanager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.Rmanager.menuService.model.entity.Categoria;
import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.service.ProductosService;
import co.edu.poli.Rmanager.usuarioService.service.EmpleadoService;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Main {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	ProductosService productoService; 
	
	public static void main (String[] args) {
		
		Main m = new Main();
		
		//pruebas empleado
		

		
		//pruebas producto
		ArrayList<Producto> pl = new ArrayList<Producto>();
		
		Categoria comidaRapida = new Categoria((long) 1, "Comida Rapida", pl);
		
		
		Producto p1 = new Producto((long) 1, "Perro Caliente", "Salchicha dentro de un pan", (double) 15.0, comidaRapida, true);
		
		Producto p2 = new Producto((long) 1, "Pizza", "Pizza italiana tradiconal ig", (double) 15.0, comidaRapida, true);

		Producto p3 = new Producto((long) 1, "Hamburguesa", "Carne molida dentro de un pan", (double) 15.0, comidaRapida, true);

		m.productoService.createProducto(p1);
		
		m.productoService.createProducto(p2);
		
		m.productoService.createProducto(p3);
		
		
		
		
		
		//pruebas detalle_producto
	}
	

}
