package cl.chicheriabueno.microservicio_inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("productos")
public class Producto {

    @Id
    private Long productoId;
    private String sku;
    private String productoNom;
    private String descripcion;
    private String imagenProductoUrl;
    private BigDecimal precioBase;
    private BigDecimal gradoAlchol;
    private Boolean aplicaIla;
    private Integer stock;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    
}
