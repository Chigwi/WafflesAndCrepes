package co.edu.poli.Rmanager.ordenService.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Mesa")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {
	
	@Id
	private Long id;
	
	@Column(name = "cupo")
	private byte cupo;
	
	@Column(name = "ocupada")
	private Boolean ocupada;
	
	
}

