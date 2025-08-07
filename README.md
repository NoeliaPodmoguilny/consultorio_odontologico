# Consultorio Odontológico

## Descripción

Este proyecto es una **primera versión** de un sistema web para la gestión de un consultorio odontológico. Es un **proyecto académico**, desarrollado con fines de práctica y aprendizaje, por lo que tanto la arquitectura como las tecnologías empleadas pueden evolucionar en futuras versiones.

El objetivo es brindar una base funcional para la administración de pacientes, turnos y profesionales de un consultorio dental, contemplando aspectos de seguridad y una estructura fácilmente escalable.

---

## Estado del Proyecto

- **Primera versión funcional**
- **Backend:** Implementado con Java 23, Spring Boot, JPA, y MySQL/H2.
- **Frontend:** Simple y a modo de ejemplo, desarrollado con HTML, CSS, Bootstrap y Javascript puro.
- **Seguridad:** Incluye autenticación y autorización con Spring Security, encriptación de contraseñas (bcrypt), y manejo de JWT para sesiones seguras.
- **OAuth:** Preparado para autenticación con OAuth2.

### NOTA SOBRE EL FRONTEND

El frontend es **básico** y solo cumple la función de mostrar el flujo de trabajo y consumo de la API. La idea es **refactorizarlo y migrar a un framework moderno como React.js** en próximas etapas del desarrollo.

---

## Tecnologías Utilizadas

### Backend

- **Java 23**
- **Spring Boot 3.3.3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring OAuth2 Client
- **Bases de datos:** H2 (entorno local/testing), MySQL (producción/despliegue)
- **JWT (Java Web Tokens)** para autenticación
- **Maven** para gestión de dependencias y build

#### Dependencias principales (`pom.xml`)

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-oauth2-client`
- `h2` (runtime)
- `mysql-connector-java`
- `java-jwt`
- `spring-security-crypto`
- `jakarta.validation-api`

### Frontend

- **HTML5**
- **CSS3**
- **Bootstrap**
- **Javascript Vanilla**

---

## Instalación y Ejecución

### Prerrequisitos

- Java 23 o superior
- Maven
- MySQL (opcional, para persistencia fuera de H2)
- Navegador web moderno

### Clonar el repositorio

```bash
git clone https://github.com/NoeliaPodmoguilny/consultorio_odontologico.git
cd consultorio_odontologico
```

### Backend

1. Ajustar `application.properties` con los datos de tu base de datos y configurar JWT.
  ```bash
  # Configuración de Base de datos
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url= ${DB_URL}
  spring.datasource.username=${DB_USERNAME}
  spring.datasource.password= ${DB_PASS}
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  
  #Configuración de JWT
  security.jwt.private.key=${JWT_KEY}
  security.jwt.user.generator=${JWT_USERNAME}
  ```
3. Compilar y ejecutar:
   ```bash
   mvn spring-boot:run
   ```

### Frontend

- Abrir el archivo `index.html` en tu navegador.
- El frontend se comunica con el backend a través de peticiones HTTP.

---

## Futuras Mejoras

- Migrar el frontend a **React.js** u otro framework moderno.
- Mejorar la experiencia de usuario y responsividad.
- Agregar tests unitarios y de integración más robustos.
- Despliegue en la nube y CI/CD.
- Documentación de API (Swagger/OpenAPI).

---

## Contribuciones

Toda contribución es bienvenida. Recuerda que este es un proyecto académico y un espacio de aprendizaje.

---

## Licencia

Este proyecto es de uso académico y sin fines comerciales. Puedes utilizarlo, modificarlo y compartirlo citando el repositorio original.

---

**Desarrolladora:** [Noelia Podmoguilny](https://github.com/NoeliaPodmoguilny)
