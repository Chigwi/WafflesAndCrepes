package co.edu.poli.Rmanager.usuarioService.model.entity;

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
@Table(name = "Rol")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

	
	@Id
	private Long id_rol;

	@Column
	private String nombre;
	
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Empleado> empleados;
    
}
