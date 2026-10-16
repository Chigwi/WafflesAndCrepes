package co.edu.poli.Rmanager.ordenService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaActivaDTO {
	
	private String nombreEmpleado;
	
	private Long mesaId;
	
	private String estado;

}
