package cl.chicheriabueno.microservicio_inventario.repository;

import cl.chicheriabueno.microservicio_inventario.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    
    // Buscar productos por su SKU
    Mono<Producto> findBfindBySku(String sku);

    // Consultar catálogo
    Flux<Producto> findByActivoTrue();

    // Alerta al KPI producto con stock inferior al límite
    Flux<Producto> findByStockLessThan(Integer stockMin);

}
