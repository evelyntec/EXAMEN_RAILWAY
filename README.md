# 🖼️ Galería de pinturas · Spring Boot

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot&logoColor=white) ![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring%20Security-BCrypt-6DB33F?logo=springsecurity&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-multi--stage-2496ED?logo=docker&logoColor=white) ![JSP](https://img.shields.io/badge/Vistas-JSP%20%2B%20JSTL-6DB33F)

Aplicación web completa para publicar, administrar y **comprar copias de pinturas**, con registro e inicio de sesión de usuarios. Proyecto de examen final del módulo de Spring, preparado para desplegarse en **Railway** mediante Docker.

> Examen final del **Bootcamp Full Stack Java (2026)**.

## ✨ Funcionalidades

- **Registro e inicio de sesión** con manejo de sesión (`HttpSession`) y contraseñas cifradas con **BCrypt**.
- Catálogo de pinturas con imagen, año, descripción, precio y copias disponibles.
- **Crear, editar y eliminar** pinturas; solo quien la publicó puede eliminarla.
- **Comprar copias**, con descuento automático del stock.
- Historial de compras del usuario.
- Validaciones completas: títulos únicos, números positivos y URL de imagen válida.

## 🏗️ Arquitectura

```
src/main/java/com/pinturas/
├── configuracion/   ← Spring Security y BCrypt
├── controladores/   ← autenticación, pinturas y compras
├── modelos/         ← Usuario, Pintura, Compra (JPA)
├── repositorios/    ← Spring Data JPA
└── servicios/       ← reglas de negocio
src/main/webapp/WEB-INF/vistas/  ← vistas JSP con fragmento de menú reutilizable
```

**Modelo de datos:** un `Usuario` crea muchas `Pintura`; una `Compra` relaciona a un usuario con una pintura.

## ✨ Rutas principales

| Método | Ruta | Descripción |
|---|---|---|
| `GET` / `POST` | `/registro` | Registro de usuarios |
| `GET` / `POST` | `/login` | Inicio de sesión |
| `GET` | `/logout` | Cierre de sesión |
| `GET` | `/pinturas` | Catálogo |
| `GET` | `/pinturas/{id}` | Detalle de una pintura |
| `GET` / `POST` | `/pinturas/nueva` | Publicar una pintura |
| `GET` / `POST` | `/pinturas/{id}/editar` | Editar |
| `POST` | `/pinturas/{id}/eliminar` | Eliminar |
| `POST` | `/pinturas/{id}/comprar` | Comprar una copia |
| `GET` | `/compras` | Mis compras |

## ▶️ Cómo ejecutarlo en local

Requisitos: JDK 17, Maven 3.9 y MySQL 8.

```bash
git clone https://github.com/evelyntec/galeria-pinturas-spring-boot.git
cd galeria-pinturas-spring-boot
export DB_USER=root
export DB_PASSWORD=tu_contraseña
mvn spring-boot:run
```

La base de datos `pinturas_db` se crea automáticamente. Para cargar pinturas de ejemplo, ejecuta `seed_pinturas.sql` después del primer arranque.

## 🐳 Despliegue con Docker / Railway

El `Dockerfile` usa una **construcción en dos etapas**: compila con Maven y ejecuta el `.war` sobre una imagen liviana de Java 17.

```bash
docker build -t galeria-pinturas .
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://HOST:3306/pinturas_db -e DB_USER=usuario -e DB_PASSWORD=clave galeria-pinturas
```

En Railway basta con conectar el repositorio y definir las variables `SPRING_DATASOURCE_URL`, `DB_USER` y `DB_PASSWORD` (o `SPRING_DATASOURCE_USERNAME` / `SPRING_DATASOURCE_PASSWORD`).

---

## 👩‍💻 Autora

**Evelyn Álvarez Vásquez** · Técnica en Informática en formación (IPLACEX) · Profesora y Magíster en Didáctica de la Matemática

[![LinkedIn](https://img.shields.io/badge/LinkedIn-profesoraevelyn-0A66C2?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/profesoraevelyn/)
[![GitHub](https://img.shields.io/badge/GitHub-evelyntec-181717?logo=github&logoColor=white)](https://github.com/evelyntec)
[![Web](https://img.shields.io/badge/Web-profesoraevelyn.com-00B8D9?logo=googlechrome&logoColor=white)](https://profesoraevelyn.com)
