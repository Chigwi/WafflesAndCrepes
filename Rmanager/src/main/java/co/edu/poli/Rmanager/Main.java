package co.edu.poli.Rmanager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.Rmanager.menuService.model.entity.Categoria;
import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.service.ProductosService;
import co.edu.poli.Rmanager.ordenService.model.Entity.Detalle_pedido;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.model.entity.Rol;
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
		
		Rol mesero = new Rol((long) 1, "Mesero", null);
		
		Empleado e1 = new Empleado((long) 1, "Sam", "Arce", "12345678", "Sam@correo", mesero, null);
		
		Empleado e2 = new Empleado((long) 2, "Allie", "Velandia", "109315345", "Allie@correo", mesero, null);
		
		Empleado e3 = new Empleado((long) 3, "Salomé", "Dorado", "11361646", "Salo@correo", mesero, null);
		
		m.empleadoService.createEmpleado(e1);
		
		m.empleadoService.createEmpleado(e2);
		
		m.empleadoService.createEmpleado(e3);
		
		
		System.out.println(m.empleadoService.getAllEmpleado().toString());
		
		Empleado e4 = new Empleado((long) 1, "Allyson", "Velandia", "109315345", "Allie@correo", mesero, null);
		
		System.out.println(m.empleadoService.getUsuarioById((long)2));
		

		
		//pruebas producto
		ArrayList<Producto> pl = new ArrayList<Producto>();
		
		Categoria comidaRapida = new Categoria((long) 1, "Comida Rapida", pl);
		
		
		Producto p1 = new Producto((long) 1, "Perro Caliente", "Salchicha dentro de un pan", (double) 15.0, comidaRapida, true);
		
		Producto p2 = new Producto((long) 2, "Pizza", "Pizza italiana tradiconal ig", (double) 15.0, comidaRapida, true);

		Producto p3 = new Producto((long) 3, "Hamburguesa", "Carne molida dentro de un pan", (double) 15.0, comidaRapida, true);

		m.productoService.createProducto(p1);
		
		m.productoService.createProducto(p2);
		
		m.productoService.createProducto(p3);
		
		System.out.println(m.productoService.getAllProducto().toString());
		
		Producto p4 = new Producto((long) 3, "Salchipapa", "papas y salchichas", (double) 15.0, comidaRapida, true);
		
		System.out.println(m.productoService.getProductoById((long)3).toString());
		
	
		
		
		//pruebas detalle_producto
		
		Detalle_pedido sp = new Detalle_pedido();
		
		Detalle_producto pd1 = new Detalle_producto((long)1,p1,"sin cebolla",sp);
		
		Detalle_producto pd2 = new Detalle_producto((long)2,p2,"con adicion de queso",sp);
		
		Detalle_producto pd3 = new Detalle_producto((long)3,p3,"con adicion de papas a la francesa",sp);
		
		m.productoService.createDetalleProducto(pd1);
		m.productoService.createDetalleProducto(pd2);
		m.productoService.createDetalleProducto(pd3);
		
		System.out.println(m.productoService.getAllDetalleProducto().toString());
		
		Detalle_producto pd4 = new Detalle_producto((long)1, p1, "sin cebolla y con limonada",sp);
		
		System.out.println(m.productoService.getDetalleProductonById((long)1).toString());
		
		
		//deletes
		
		//empleado
		m.empleadoService.deleteEmpleado((long)1);
		m.empleadoService.deleteEmpleado((long)2);
		m.empleadoService.deleteEmpleado((long)3);
		
		//producto
		m.productoService.deleteProducto((long)1);
		m.productoService.deleteProducto((long)2);
		m.productoService.deleteProducto((long)3);
		
		//detalle prpodcuto
		m.productoService.deleteDetalle_producto((long)1);
		m.productoService.deleteDetalle_producto((long)2);
		m.productoService.deleteDetalle_producto((long)3);
		
		
		
		
	}
	

}
