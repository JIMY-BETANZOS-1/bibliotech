# BiblioTech

Sistema de préstamos bibliográficos (backend Spring Boot).

## Reglas de negocio implementadas

- Un estudiante no puede tener más de 3 préstamos activos.
- No se presta un libro sin ejemplares disponibles.

## Ejecutar

```bash
mvn test            # pruebas JUnit
mvn spring-boot:run # API en http://localhost:8080
```

`POST /api/loans` con `{"studentId": 1, "bookId": 3}` solicita un préstamo.
Responde 201 si es válido o 409 con el motivo si incumple una regla.
