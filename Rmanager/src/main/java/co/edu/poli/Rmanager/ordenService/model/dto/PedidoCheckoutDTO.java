package co.edu.poli.Rmanager.ordenService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCheckoutDTO {
	
	private Long idPedido;
	
	private Long idEmpleado;

}
