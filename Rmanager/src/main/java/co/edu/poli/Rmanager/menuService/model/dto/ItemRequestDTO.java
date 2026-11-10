package co.edu.poli.Rmanager.menuService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    private Long idProducto;
    private Integer cantidad;
    private String notas;
}