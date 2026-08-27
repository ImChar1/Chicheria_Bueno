# Chichería Bueno - E-Commerce and Security Sandbox

Plataforma web de e.commerce y hestión de inventario para la comercialización de chicha artesanal chilena. Este proyecto funciona como un **laboratorio técnico (*sandbox*)** para la experimentación con microservicios en java, auditoría de ciberseguridad aplicando OWASP Top 10, planes de pruebas funcionales y no funcionales además de migraciones de infraestructura *On-Premise* a *Cloud*.

---

## Stack Tecnológico

* **Frontend:** Next.Js + TypeScript (SSR, Container/Presenter Pattern, Zustand/Redux).
* **Backend:** Microservicios en Spring Boot + Arquitectura Hexagonal.
* **API Gateway:** Spring Cloud Gateway
* **Persistencia y Caché:** MariaDB (BD per Service) + Redis.
* **Infraestructura:** Desplieguie inicial On-Premise con proyección a Docker / Docker Compose.

---

**Instrucciones para Testear la Api Actualmente:**

* Ejecutar la app
Abre la terminal en la carpeta /Desarrollo/backend/api-gateway y ejecuta el siguiente comando.

* En Linux / macOS:
./mvnw spring-boot:run

* En Windows / CMD:
mvnw.cmd spring-boot:run

* En PowerShell:
.\mvnw.cmd spring-boot:run

* Prueba de Endpoint en la terminal o en el navegador:
curl http://localhost:8080/actuator/health

* Prueba de Ruteo y Headers con Ruta Temporal en terminal o navegador:
curl http://localhost:8080/api/v1/test/headers

* Prueba de Microservicio Inexistente para verificar fallos:
curl.exe -i http://localhost:8080/api/v1/auth/login