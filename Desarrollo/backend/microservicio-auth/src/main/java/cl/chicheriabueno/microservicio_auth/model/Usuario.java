package cl.chicheriabueno.microservicio_auth.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("usuarios")
public class Usuario {
    
    @Id
    private Long id;
    private String rut;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
    private Boolean mayorEdad;
    private Long rolId;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
}
