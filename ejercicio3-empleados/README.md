# Ejercicio3 empleados — API de Empleados

Aplicación Spring Boot con almacenamiento en lista en memoria, validación de campos y acceso mediante API Key.

- **Puerto:** `8083`
- **URL base:** `http://localhost:8083/api/empleados`
- **Encabezado requerido:** `X-API-KEY`
- **Campos:** `id`, `nombre`, `apellido`, `correo`, `especialidad`, `estado`.
- **Validaciones:** nombre y apellido no vacíos, correo válido.
- **Errores:** 400 para datos inválidos, 404 para ID no encontrado, 401 por API Key ausente o incorrecta.

## Ejecutar con Git Bash

```bash
export API_KEY="clave-laboratorio-vii"
mvn spring-boot:run
```

## Peticiones de ejemplo

Con la aplicación iniciada y la clave configurada en la terminal que realiza la petición:

```bash
export API_KEY="clave-laboratorio-vii"

curl -i -X POST "http://localhost:8083/api/empleados" \
  -H "X-API-KEY: $API_KEY" -H "Content-Type: application/json" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana@example.com","especialidad":"Tecnologia","estado":true}'

curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8083/api/empleados"
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8083/api/empleados?especialidad=Tecnologia"
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8083/api/empleados/1"
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8083/api/empleados/999999"
curl -i "http://localhost:8083/api/empleados"
```

## Pruebas unitarias

```bash
mvn test
```

Incluye tres pruebas con JUnit: crear, obtener registro existente y consultar registro inexistente.
