package co.edu.poli.Rmanager.ordenService.model.dto;

import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoUpdateMesaDTO {
	
	private Long id;
	
	private Long idmesa;
	

}
