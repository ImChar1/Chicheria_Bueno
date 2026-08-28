package cl.chicheriabueno.microservicio_auth.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import cl.chicheriabueno.microservicio_auth.model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, Long> {
    
    // Busqueda para iniciar sesión
    Mono<Usuario> findByCorreo(String correo);

    // Busqueda para validar rut duplicados
    Mono<Usuario> findByRut(String rut);

    // Filtro de usuarios según su rol
    Flux<Usuario> findByRolId(Long rolId);

    // Comprobar si existe email y rut
    Mono<Usuario> existByCorreo(String correo);
    Mono<Usuario> existByRut(String rut);
}
