package co.edu.poli.Rmanager.menuService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPrecioDTO {
	
	private Long idProducto;
	
	private String nombre;
	
	private Double precio;

}
