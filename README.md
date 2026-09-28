#API Básica de Videojuegos

##Descripción

La API Básica de Videojuegos es una aplicación REST desarrollada con Java y Spring Boot para administrar un catálogo básico de videojuegos mediante operaciones CRUD.

La aplicación permite crear, consultar, actualizar y eliminar videojuegos, utilizando Spring Data JPA, Hibernate y MySQL como sistema de persistencia.

El proyecto implementa una relación entre las entidades "VideoJuego" y "Genero", donde cada videojuego pertenece a un género. Además, se consume una API externa mediante "RestClient", se implementa manejo de errores y se incorporan mecanismos de observabilidad mediante Spring Boot Actuator, Micrometer y Prometheus.

#Integrante

Jeison Quintero Álvarez

#Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- RestClient
- Spring Boot Actuator
- Micrometer
- Prometheus
- Bruno
- GitHub

#Persistencia de datos

La aplicación utiliza MySQL como sistema de persistencia.

La base de datos utilizada es:

"videojuegos"

La conexion con MySQL se realiza mediante variables de entorno para evtar almacenar las credenciales directamente en el codigo.

Se utilizaron las siguientes variables:

DB_URL=jdbc:mysql://localhost:3306/videojuegos
DB_USERNAME=root
DB_PASSWORD=TU_CONTRASEÑA

Las variables mencionadas anteriormente se ejecutan el powershell del visual studio para asi establecer la respectiva conexión.

Por otro lado, en este proyecto se establecieron 2 entidades a relacionar las cuales fueron: GENERO y VideoJuego.

Entre estas 2 entidades existe una relación ManyToOne. Lo que significa que un genero pueda estar relacionado a varios videojuegos, pero cada videojuego pertenece a un genero.

Algunos endpoints que se establecieron fueron:

GET/videojuegos --> El cual consulta el listado de los videojuegos registrados

GET /videojuegos/{id} --> Permite la consulta de un videojuego en especifico por medio de su ID

GET /videojuegos/buscar?genero={genero} --> Permite la consulta de los videojuegos segun su genero

POST /videojuegos --> Permite crear un videojuego por medio de una estrctura JSON

Ej: 
{
  "nombre": "Minecraft",
  "generoId": 2,
  "plataforma": "PC"
}

PUT /videojuegos/{id} --> Permite la modificación de los datos de un videojuego en especifico

DELETE /videojuegos/{id} --> Permite la eliminació  de un videojuego en especifico

Asimismo, la aplicación consume una API externa utilizando RestClient, esta api externa se llama JSONPlaceholder y además se cuenta con un manejo de errores en caso tal de que haya un problema durante la comunicación con la api externa

Por el lado de observabilidad se implemento Sprin Boot Actuator, Micrometer y Prometeus

Health Check 

GET /actuator/health --> Permite verificar el estado de los diferentes componentes de la aplicación y para ver que todo este OK tiene que tener un estado "UP"

Las métricas planteadas fueron las siguientes:

GET /actuator/metrics

Métrica personalizada

Se implemento la siguiente metrica
api.externa.consultas
La cual contabiliza la cantidad de cosnultas realizadas en la API Externa

La configuración de ACTUATOR se habilio mediante los siguientes endpoints:

management.endpoints.web.exposure.include=health,metrics,prometheus
management.endpoint.health.show-details=always

http://localhost:8080/actuator/health
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/api.externa.consultas
http://localhost:8080/actuator/prometheus

Por ultimo, cabe resaltar que todas las pruebas de los endpoints fueron realizadas mediante Bruno, validando que las diferentes respuestas fueran acorde a lo solicitado.
Codigos

200--> OK
201--> Creado
204--> Eliminado
404--> No encontrado

Y para ejecutar el proyecto en general, se debe iniciar la aplicación de SpringBoot con el comando: 

.\mvnw.cmd spring-boot:run

Y la api estaría disponible en el siguiente enlace:  http://localhost:8080