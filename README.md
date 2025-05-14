TallerCarPro
TallerCarPro es una aplicación backend para la gestión de talleres automotrices, desarrollada con Spring Boot 3.4.5 y Java 17. Permite administrar clientes, vehículos, facturas, reparaciones, mecánicos, repuestos, proveedores y balances financieros, con una base de datos MySQL para almacenamiento persistente. ¡Perfecta para optimizar las operaciones de un taller! 🚗🔧
Características

Gestión de Clientes: Crear, leer, actualizar y eliminar (CRUD) clientes con información como nombre, identificación y teléfono.
Gestión de Vehículos: Registrar vehículos con placa, marca, modelo, año y cliente asociado.
Facturación: Crear facturas con costos de mano de obra, repuestos y otros, asociadas a clientes y vehículos.
Reparaciones: Gestionar reparaciones con detalles como tipo, estado, costo y mecánico asignado.
Mecánicos: Registrar mecánicos con información de contacto y pago semanal.
Repuestos: Administrar repuestos con costo, descripción y porcentaje de ganancia.
Proveedores: Gestionar proveedores y sus facturas, asociadas a vehículos y repuestos.
Balances: Calcular balances financieros con ingresos, gastos y fechas específicas.
Base de Datos: Integración con MySQL para almacenamiento persistente.
API REST: Endpoints para interactuar con todas las entidades.

Tecnologías

Java: 17 (recomendado, aunque el proyecto puede ejecutarse en Java 24 con precaución)
Spring Boot: 3.4.5
Spring Data JPA: Para la persistencia de datos
MySQL: Base de datos relacional
Maven: Gestión de dependencias
Lombok: Para reducir código boilerplate
Hibernate: ORM para mapear entidades a la base de datos

Requisitos Previos

Java 17 (instala OpenJDK 17: sudo apt install openjdk-17-jdk)
Maven (instala Maven: sudo apt install maven)
MySQL (instala MySQL Server: sudo apt install mysql-server)
Git (para clonar el repositorio: sudo apt install git)
IDE recomendado: IntelliJ IDEA, Eclipse o VS Code

Instalación

Clona el repositorio:
git clone https://github.com/tu-usuario/tallercarpro.git
cd tallercarpro/tallercarpro/appTaller


Configura MySQL:

Crea la base de datos:CREATE DATABASE tallercarpro;


Actualiza src/main/resources/application.properties con tus credenciales de MySQL:spring.datasource.url=jdbc:mysql://localhost:3306/tallercarpro?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false
server.port=8080




Instala dependencias:
mvn clean install


Ejecuta la aplicación:
mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080.


Estructura del Proyecto
tallercarpro/
├── appTaller/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/tallercarpro/appTaller/
│   │   │   │   ├── controller/    # Controladores REST
│   │   │   │   ├── models/domain/ # Entidades JPA
│   │   │   │   ├── repository/    # Repositorios JPA
│   │   │   │   ├── service/       # Lógica de negocio
│   │   │   │   └── AppTallerApplication.java # Clase principal
│   │   │   ├── resources/
│   │   │   │   └── application.properties # Configuración
│   ├── pom.xml # Dependencias Maven
│   └── README.md # Este archivo

Entidades Principales

Balance: Gestiona balances financieros (ingresos, gastos, fechas).
Client: Almacena datos de clientes (nombre, identificación, teléfono).
Invoice: Registra facturas con costos y método de pago.
Mechanic: Gestiona mecánicos (nombre, teléfono, pago semanal).
Vehicle: Registra vehículos (placa, marca, modelo, cliente).
Repair: Administra reparaciones (descripción, costo, mecánico).
SparePart: Gestiona repuestos (nombre, costo, proveedor).
Supplier: Almacena datos de proveedores (nombre, contacto).
SupplierInvoice: Registra facturas de proveedores (total, vehículo).

Endpoints de Ejemplo
Usa herramientas como curl o Postman para probar los endpoints. A continuación, algunos ejemplos:

Crear un cliente:
curl -X POST http://localhost:8080/api/clients -H "Content-Type: application/json" -d '{"name":"John Doe","identification":"123456789","phone":"+1234567890"}'


Crear un vehículo:
curl -X POST http://localhost:8080/api/vehicles -H "Content-Type: application/json" -d '{"licensePlate":"ABC123","brand":"Toyota","model":"Corolla","year":2020,"client":{"id":"client-id"}}'


Crear una factura de proveedor:
curl -X POST http://localhost:8080/api/supplier-invoices -H "Content-Type: application/json" -d '{"date":"2025-05-14T10:00:00","description":"Spare parts","total":500.0,"paid":false,"vehicle":{"id":"vehicle-id"},"supplier":{"id":"supplier-id"}}'


Obtener todos los balances:
curl -X GET http://localhost:8080/api/balances



Nota: Reemplaza client-id, vehicle-id, y supplier-id con IDs válidos generados por la aplicación.
Base de Datos
La aplicación usa MySQL con spring.jpa.hibernate.ddl-auto=update, que crea/actualiza las tablas automáticamente. Las tablas incluyen:

balance: Balances financieros.
clients: Clientes.
invoices: Facturas.
mechanics: Mecánicos.
vehicles: Vehículos.
repairs: Reparaciones.
spare_parts: Repuestos.
suppliers: Proveedores.
supplier_invoices: Facturas de proveedores.
repair_spare_parts: Relación muchos a muchos entre reparaciones y repuestos.

Verifica las tablas en MySQL:
USE tallercarpro;
SHOW TABLES;
SELECT * FROM clients;

Solución de Problemas

Error de puerto ocupado:

Verifica el puerto 8080:lsof -i :8080


Termina el proceso o cambia el puerto en application.properties:server.port=8081




Errores de base de datos:

Asegúrate de que MySQL esté corriendo:sudo systemctl start mysql


Verifica las credenciales en application.properties.


Advertencias de Hibernate:

Si ves advertencias sobre constructores sin argumentos, asegúrate de que todas las entidades usen @NoArgsConstructor.
Si encuentras LazyInitializationException, revisa las relaciones en los servicios o considera habilitar spring.jpa.open-in-view=true (con precaución).


Logs detallados:

Habilita el modo debug:mvn spring-boot:run -Dspring-boot.run.arguments=--debug





Contribuir

Haz un fork del repositorio.
Crea una rama para tu feature:git checkout -b feature/nueva-funcionalidad


Commitea tus cambios:git commit -m "Agrega nueva funcionalidad"


Sube la rama:git push origin feature/nueva-funcionalidad


Abre un Pull Request.

Próximos Pasos

Despliegue: Configurar el despliegue en Heroku o AWS.
Frontend: Integrar un frontend con Electron o React.
Pruebas: Agregar pruebas unitarias y de integración con JUnit y Mockito.
Seguridad: Implementar autenticación y autorización con Spring Security.


