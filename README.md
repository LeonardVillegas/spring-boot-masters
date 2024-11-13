# spring-boot-masters
Este proyecto es el backend de la aplicación Estramipime, diseñada para la gestión y optimización de estrategias y operaciones empresariales. El backend está desarrollado en Spring Boot con Java y expone una API REST que permite gestionar usuarios, estrategias, reportes, y más funcionalidades de la aplicación Estramipime.

## Integrantes
* Leonard Villegas: villegas.leonard@gmail.com

* Alejandra Infante: alejandrainfant@gmail.com

* Angie Arango: angie.a.zapata@gmail.com

* Edwin Cuteño: edwincuteno12@gmail.com

* Reinaldo Rojas Lopez: rojas.reinaldo@hotmail.com

* Melissa Garcìa Turizo: melissagiseelle@gmail.com

* Yuliana Andrea Echavarria Arboleda: yuliandre16.07@hotmail.com


# Documentación de la API
Esta aplicación utiliza Springdoc OpenAPI para generar documentación interactiva de la API, accesible a través de Swagger UI. A continuación, se indican los pasos para acceder a la documentación de la API:

## Acceso a Swagger UI
* **Inicia la aplicación:** Asegúrate de que la aplicación está ejecutándose en tu entorno local.

* **Navega a Swagger UI:** Abre tu navegador y ve a la siguiente URL para acceder a la documentación interactiva:

http://localhost:8080/swagger-ui/index.html

La interfaz de Swagger UI te permitirá explorar todos los endpoints disponibles en la API, junto con detalles como los métodos HTTP, parámetros, y respuestas esperadas.
También puedes probar los endpoints directamente desde la interfaz, ingresando parámetros y ejecutando solicitudes.

# Inicialización de la Base de Datos
La aplicación contiene un método de inicialización de la base de datos que carga datos desde el archivo `db.json` ubicado en `src/main/resources/static/db.json. Este método está implementado en la clase `DatabaseConfig` y utiliza el archivo JSON para poblar la base de datos con registros de preguntas.

## Funcionamiento de la Inicialización
* Ejecución al Inicio: El método de inicialización se ejecuta automáticamente cuando la aplicación inicia, usando un `ApplicationRunner` para cargar los datos.

* Verificación de Datos Existentes: Antes de cargar los datos desde `db.json`, el método verifica si ya existen registros en la tabla de preguntas (`Question`) de la base de datos.

* Si no hay registros en la base de datos, el método cargará los datos de db.json y los guardará en la base de datos.
* Si ya existen registros en la base de datos, el método omitirá la carga de datos para evitar duplicados.
## Detalles Técnicos
El método utiliza el servicio `QuestionService` para comprobar si hay preguntas en la base de datos y para guardar nuevos registros en caso de que la base de datos esté vacía.

## Contribuciones

¡Agradecemos tus contribuciones! Por favor, revisa el archivo [`CONTRIBUTING.md`](./CONTRIBUTING.md) para obtener más detalles sobre cómo puedes colaborar.
