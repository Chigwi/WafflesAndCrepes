package co.edu.poli.Rmanager.menuService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
	
    private Long idDetalle;
    
    private Long idProducto;
    
    private String nombre;
    
    private Integer cantidad;
    
    private Double precioUnitario;
    
    private Double subtotal;
    
    private String notas;
    
}