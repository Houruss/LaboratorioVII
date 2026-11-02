# Laboratorio VII: Preparación para Examen Final

**Programación II — APIs | Universidad Mariano Gálvez de Guatemala**

Tres ejercicios independientes realizados con **Java 21 y Spring Boot 3**. Cada uno almacena sus registros en una **lista en memoria**, valida los datos de entrada y requiere una **API Key** para acceder a sus endpoints.

## Estructura del repositorio

```text
LaboratorioVII/
├── ejercicio1-profesores/
│   ├── pom.xml
│   └── src/main/ y src/test/
├── ejercicio2-proveedores/
│   ├── pom.xml
│   └── src/main/ y src/test/
├── ejercicio3-empleados/
│   ├── pom.xml
│   └── src/main/ y src/test/
├── .gitignore
└── README.md
```

Cada ejercicio organiza sus clases en `controller`, `service`, `model`, `dto`, `exception` y `security`. Incluye tres pruebas unitarias con JUnit 5.

## Requisitos

- Java JDK 21 o superior.
- Maven 3.9 o superior.
- Git y **Git Bash** para el control de versiones.

Comprueba que las herramientas estén disponibles desde Git Bash:

```bash
java -version
mvn -version
git --version
```

## Ejecución de los ejercicios

Abre **tres terminales Git Bash** desde la carpeta del laboratorio. En cada una, configura una API Key local (el valor es un ejemplo para prácticas) y ejecuta el proyecto correspondiente:

**Terminal 1 — Profesores**

```bash
cd ejercicio1-profesores
export API_KEY="clave-laboratorio-vii"
mvn spring-boot:run
```

**Terminal 2 — Proveedores**

```bash
cd ejercicio2-proveedores
export API_KEY="clave-laboratorio-vii"
mvn spring-boot:run
```

**Terminal 3 — Empleados**

```bash
cd ejercicio3-empleados
export API_KEY="clave-laboratorio-vii"
mvn spring-boot:run
```

Si los inicias de forma separada, puedes detener cada aplicación con `Ctrl+C`. Los datos en memoria se reinician al volver a ejecutar la aplicación.

## Endpoints y códigos HTTP

| Ejercicio | URL base | Filtro |
|---|---|---|
| Profesores | `http://localhost:8081/api/profesores` | `?especialidad=Tecnologia` |
| Proveedores | `http://localhost:8082/api/proveedores` | `?categoria=Tecnologia` |
| Empleados | `http://localhost:8083/api/empleados` | `?especialidad=Tecnologia` |

Para cada ejercicio:

- `POST /api/<recurso>`: crea un registro (**201 Created**).
- `GET /api/<recurso>`: lista todos los registros (**200 OK**).
- `GET /api/<recurso>?especialidad=...` o `?categoria=...`: filtra registros (**200 OK**).
- `GET /api/<recurso>/{id}`: devuelve el registro (**200 OK**) o **404 Not Found** si no existe.
- Datos inválidos en la creación: **400 Bad Request**.
- API Key ausente o incorrecta: **401 Unauthorized**.

**Todas las peticiones a `/api/` deben enviar el encabezado `X-API-KEY`.**

## Ejemplos de pruebas desde Git Bash

Con la API de profesores ejecutándose, en otra terminal Git Bash:

```bash
export API_KEY="clave-laboratorio-vii"

# Crear profesor
curl -i -X POST "http://localhost:8081/api/profesores" \
  -H "X-API-KEY: $API_KEY" \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana@example.com","especialidad":"Tecnologia","estado":true}'

# Listar profesores
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8081/api/profesores"

# Filtrar por especialidad
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8081/api/profesores?especialidad=Tecnologia"

# Consultar profesor existente (ID 1 si es el primer registro creado)
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8081/api/profesores/1"

# Consultar profesor inexistente (404)
curl -i -H "X-API-KEY: $API_KEY" "http://localhost:8081/api/profesores/999999"

# API Key ausente (401)
curl -i "http://localhost:8081/api/profesores"

# Correo inválido y nombre vacío (400)
curl -i -X POST "http://localhost:8081/api/profesores" \
  -H "X-API-KEY: $API_KEY" \
  -H "Content-Type: application/json" \
  -d '{"nombre":"","apellido":"Lopez","correo":"no-es-correo","especialidad":"Tecnologia","estado":true}'
```

Los `README.md` de cada ejercicio incluyen sus propias peticiones para crear, listar, filtrar y consultar registros.

## Pruebas unitarias

Desde **cada carpeta de ejercicio**, ejecuta:

```bash
mvn test
```

El resultado esperado por ejercicio es **3 pruebas ejecutadas, 0 fallidas y 0 errores**: crear, obtener existente y obtener inexistente. En total, los tres ejercicios incluyen **9 pruebas unitarias**.

## Git Bash y GitHub

Crea un repositorio **vacío** en GitHub. Luego, desde la carpeta `LaboratorioVII` en Git Bash:

```bash
git init
git branch -M main

git add .gitignore README.md
git commit -m "Add laboratory structure and instructions"

git add ejercicio1-profesores
git commit -m "Implement professors API and unit tests"

git add ejercicio2-proveedores
git commit -m "Implement suppliers API and unit tests"

git add ejercicio3-empleados
git commit -m "Implement employees API and unit tests"

git remote add origin https://github.com/TU_USUARIO/LaboratorioVII.git
git push -u origin main
```

Reemplaza `TU_USUARIO` por tu usuario real de GitHub. Si no tienes aún la identidad de Git configurada, define primero `git config --global user.name` y `git config --global user.email`. Revisa el historial con `git log --oneline`.

