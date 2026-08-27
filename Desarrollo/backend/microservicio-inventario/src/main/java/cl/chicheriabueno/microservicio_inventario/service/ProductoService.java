package cl.chicheriabueno.microservicio_inventario.service;

import cl.chicheriabueno.microservicio_inventario.model.Producto;
import cl.chicheriabueno.microservicio_inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ProductoService {
    
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Flux<Producto> mostrarCatalogoActivo() {
        return productoRepository.findByActivoTrue();
    }

    public Mono<Producto> guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Calculo de precio bruto con IVA + ILA
    public Mono<BigDecimal> calcularPrecioFinal(Long productoId) {
        return productoRepository.findById(productoId)
            .map(producto -> {BigDecimal precioBase = producto.getPrecioBase();
                              BigDecimal iva = new BigDecimal("0.19");
                              BigDecimal ila = BigDecimal.ZERO;

                              if (Boolean.TRUE.equals(producto.getAplicaIla())) {
                                // Si el grado de alchól es menor o igual a 15 es Chicha Tradicional
                                if (producto.getGradoAlchol().compareTo(new BigDecimal("15.0")) <= 0) {
                                    ila = new BigDecimal("0.205"); // =20.5%
                                } else {
                                    ila = new BigDecimal("0.315"); // =31.5%
                                }
                              }

                              BigDecimal factorImpuestos = BigDecimal.ONE.add(iva).add(ila);
                              return precioBase.multiply(factorImpuestos).setScale(0, RoundingMode.HALF_UP);
            });
    }

}
