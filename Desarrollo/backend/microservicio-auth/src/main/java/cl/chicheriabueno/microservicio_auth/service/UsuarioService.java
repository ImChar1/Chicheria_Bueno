package cl.chicheriabueno.microservicio_auth.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import cl.chicheriabueno.microservicio_auth.model.Usuario;
import cl.chicheriabueno.microservicio_auth.repository.UsuarioRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Registrio de usuario cliente verificando la edad legal para la venta de alchol
    public Mono<Usuario> registrarUsuario(Usuario usuario) {
        // Asignar rol Cliente por defecto "id: 3" especificado en resource/schema.sql
        if (usuario.getRolId() == null) {
            usuario.setRolId(3L);
        }

        if (usuario.getFechaNacimiento() != null) {
            int edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
            usuario.setMayorEdad(edad >= 18);
        } else {
            usuario.setMayorEdad(false);
        }

        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    // Vulnerabilidad Intencional como versión inicial para demostrar 
    // porque algoritmos de login simples son vulnerables.
    public Mono<Usuario> autenticarInseguro(String correo, String passwordPlana) {
        return usuarioRepository.findByCorreo(correo)
            .filter(usuario -> usuario.getPassword().equals(passwordPlana)) // Comparación en texto plano
            .switchIfEmpty(Mono.error(new RuntimeException("Credenciales inválidas")));
    }

    // Consulta KPI para equipo gerencial
    public Flux<Usuario> mostrarUsuariosPorRol(Long rolId) {
        return usuarioRepository.findByRolId(rolId);
    }

    // Vulnerabilidades:

    //Contraseña en texto plano

    //Sin Token de Sesión

    //Ausencia de Bloqueo por intentos fallidos

    //Control de identidad muy poco estricto, el cliente puede ser menor y no poder comprobarlo al momento de comprar.
}
