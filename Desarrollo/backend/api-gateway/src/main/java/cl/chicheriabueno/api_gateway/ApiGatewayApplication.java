package cl.chicheriabueno.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
/**
 * Configuración de rutas y filtros del API Gateway mediante java fluent API.
 * 
 * <P> Estructura base adaptada de la guía oficial de Spring Framework:
 * @see <a href="https://spring.io/guides/gs/gateway">Building a Gateway Guide (Spring.io)</a>
 * 
 * <p>Para consultar especificaciones tecnicas de predicados, filtros o
 * integraciones con Circuit Braker, remitirse a la documentación oficial de Spring Cloud.
 * 
 * @author Carlos Muñoz Ulloa
 * @version 1.0.0
 */

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gtwRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			///Ruta de prueba hacia internet HTTPBin entrega un JSON con la solicitud que recibió.
			.route("test-route", p-> p
				.path("/api/v1/test/**")
				.filters(f -> f
					.rewritePath("/api/v1/test/(?<segment>.*)", "/${segment}")
					.addRequestHeader("Gateway-Request", "ChicheriaBueno-Gateway"))
			.uri("https://httpbin.org"))

			.route("auth-route", p -> p
				.path("/api/v1/auth/**")
				.filters(f -> f.addRequestHeader("Gateway-Request", "ChicheriaBueno-Gateway"))
				.uri("http://localhost:8081")) ///Predicado http://localhost:8081/api/v1/auth

			.route("inventario-route", p -> p
				.path("/api/v1/inventario/**")
				.filters(f -> f.addRequestHeader("Gateway-Request", "ChicheriaBueno-Gateway"))
				.uri("http://localhost:8082")) /// Predicado http://localhost:8082/api/vi/inventario
	
			.build();
	}

}
