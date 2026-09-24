package co.edu.poli.Rmanager.usuarioService.model.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.poli.Rmanager.ordenService.model.Entity.Historial_estado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_empleado;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "cedula")
	private String cedula;
	
	@Column(name = "correo")
	private String correo;
	
	@ManyToOne
	@JoinColumn(name = "rol")
	private Long rol;
	
    @OneToMany(mappedBy = "empleado_que_relevo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Historial_estado> cambiosEstado;
	
}
