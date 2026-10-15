package co.edu.poli.Rmanager.ordenService.model.dto;

import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import lombok.Data;

@Data
public class PedidoUpdateMesaDTO {
	
	private Long id;
	
	private Mesa mesa;
	

}
