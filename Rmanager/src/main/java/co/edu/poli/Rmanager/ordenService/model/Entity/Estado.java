package co.edu.poli.Rmanager.ordenService.model.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Estado")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

	@Id
	private Long id_estado;
	
	@Column(name = "nombre")
	private String nombre;
	
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pedido> pedidos;
	
	
	
}
