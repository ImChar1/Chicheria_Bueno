package cl.chicheriabueno.microservicio_auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.chicheriabueno.microservicio_auth.model.Usuario;
import cl.chicheriabueno.microservicio_auth.service.UsuarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/auth")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // POST: Registro de clientes verificando edad
    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Usuario> registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    // POST: Login plano
    @PostMapping("/login")
    public Mono<Usuario> loginInseguro(@RequestBody LoginRequest request) {
        return usuarioService.autenticarInseguro(request.correo(), request.password());
    }
    
    // GET: Filtro de usuarios por rol para KPI
    @GetMapping("/usuario/rol/{rolId}")
    public Flux<Usuario> mostrarUsuarioPorRol(@PathVariable Long rolId) {
        return usuarioService.mostrarUsuariosPorRol(rolId);
    }

    // DTO inmutable para recibir credenciales en el JSON
    public record LoginRequest(String correo, String password) {}
}
