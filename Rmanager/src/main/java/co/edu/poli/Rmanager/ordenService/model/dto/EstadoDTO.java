package co.edu.poli.Rmanager.ordenService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
	
	private Long pedido_id;
	
	private String estado;

}
