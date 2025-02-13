# Curso Completo: APIs REST en IBM i (AS400) con RPGLE, Java y Spring Boot

Bienvenido al repositorio oficial del **Curso Completo: APIs REST en IBM i (AS400) con RPGLE, Java y Spring Boot**. Aquí encontrarás el código fuente y los recursos utilizados en las secciones enfocadas en la construcción de una API RESTful utilizando **Spring Boot** e integrando servicios con **IBM i (AS400)**.

## 📌 Descripción del Proyecto
Este proyecto es una API bancaria (**BANK-API**) que interactúa con el IBM i a través de **JARs generados en secciones previas del curso**. Se desarrollaron componentes para la gestión de clientes, cuentas y transacciones, aplicando las mejores prácticas en **Spring Boot, arquitectura limpia y conexión eficiente con AS400**.

## 📂 Estructura del Proyecto
El código está organizado en tres secciones principales:

### 🔹 Sección 10: BANK-API / Spring Boot - CLIENTE
- Creación del proyecto y configuración de conexión con **IBM i (AS400)**.
- Implementación del modelo `CUSTOMER` y su repositorio.
- Funciones para **crear, actualizar y obtener clientes**.
- Mapeo de datos desde el AS400.
- Implementación del controlador `CustomerController`.

### 🔹 Sección 11: BANK-API / Spring Boot - CUENTA
- Creación del modelo `Account`.
- Repositorio para obtener información de cuentas por número o cliente.
- Servicios para manejar detalles de cuentas.
- Controlador `AccountController` para la gestión de cuentas.

### 🔹 Sección 12: BANK-API / Spring Boot - TRANSACCIÓN
- Creación del modelo `Transaction`.
- Implementación de repositorios para gestionar transacciones.
- Servicios para realizar transacciones bancarias.
- Controlador `TransactionController`.

## 🚀 Requisitos para Ejecutar el Proyecto
Antes de ejecutar el proyecto, asegúrate de tener instalados:
- **Java 17+**
- **Spring Boot 3+**
- **Maven**
- **IBM Toolbox for Java** (para la conexión con AS400)
- **IntelliJ IDEA** (IDE recomendado)

## ⚡ Configuración del Proyecto
1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/curso-apis-rest-as400.git
   cd curso-apis-rest-as400
   ```
2. **Configura las propiedades de conexión con AS400** en `as400Connection.properties`.
3. **Instala los JARs generados previamente**:
   ```bash
   ./mvnw install:install-file -Dfile=/ruta-del-jar/SRVCLI000.jar -DgroupId=nombre_grupo -DartifactId=SRVCLI000 -Dversion=1.0 -Dpackaging=jar
   ```
4. **Actualiza el POM** desde IntelliJ IDEA 
5. **Ejecuta la aplicación** desde IntelliJ IDEA o con:
   ```bash
   ./mvnw spring-boot:run
   ```

## 📌 Endpoints Disponibles
A continuación, algunos de los endpoints principales de la API:

### Clientes
- `GET /api/v1/customer/{customerCode}` → Obtener cliente por código
- `GET /api/v1/customer/search?typeid=P&id=93027854` → Obtener cliente por identificacion
- `POST /api/v1/customer` → Crear nuevo cliente
- `PUT /api/v1/customer/{customerCode}` → Actualizar cliente

### Cuentas
- `GET /api/v1/account/{numberAccount}` → Obtener detalles de cuenta
- `GET /api/v1/account/{customerId}/all` → Obtener todas las cuentas de un cliente
- `POST /api/v1/account/` → Crear nueva cuenta

### Transacciones
- `GET /api/v1/transaction?dateFrom=2024-10-01&dateTo=2025-01-29&page=3&account=23678924565` → Obtener transacciones
- `POST /api/v1/transaction` → Crear nueva transacción

📌 **Para más detalles sobre cada endpoint, revisa los videos asociados en el curso.**

## 🛠️ Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3**
- **IBM i (AS400)**
- **JDBC & IBM Toolbox for Java**
- **Maven**
- **IntelliJ IDEA**

## 🤝 Contribuciones y Feedback
Este repositorio es parte del curso en Udemy. Si encuentras errores o mejoras, ¡no dudes en hacer un **fork** y contribuir! También puedes dejar tus dudas en la sección de preguntas del curso.

📌 **Si te gustó este curso, apóyalo con una ⭐ en GitHub!** 🚀

