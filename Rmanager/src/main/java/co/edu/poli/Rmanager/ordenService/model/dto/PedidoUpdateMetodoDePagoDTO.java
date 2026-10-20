package co.edu.poli.Rmanager.ordenService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoUpdateMetodoDePagoDTO {
	
	private Long idPedido;
	
	private byte idMetodo;

}
