package cl.chicheriabueno.microservicio_inventario.controller;

import cl.chicheriabueno.microservicio_inventario.model.Producto;
import cl.chicheriabueno.microservicio_inventario.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/inventario")    
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    // GET: http://localhost:8080/api/v1/inventario/catalogo
    @GetMapping("/catalogo")
    public Flux<Producto> mostrarCatalogo() {
        return productoService.mostrarCatalogoActivo();
    }

    // POST: http://localhost:8080/api/v1/inventario/producto
    @PostMapping("/producto")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
    
    // GET: http://localhost:8080/api/v1/inventario/precio-final/1
    @GetMapping("/precio-final/{id}")
    public Mono<BigDecimal> obtenerPrecioFinal(@PathVariable Long id) {
        return productoService.calcularPrecioFinal(id);
    }
    

}
