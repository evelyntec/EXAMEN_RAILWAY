<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Pinturas</title>
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body class="formulario-estrecho">
    <div class="superior">
        <a href="/login">Login</a>
        <a href="/registro">Registro</a>
    </div>
    <h1>Pinturas</h1>
    <h2>Login</h2>

    <form action="/login" method="post">
        <label>Correo</label>
        <input type="text" name="correo"/>

        <label>Contraseña</label>
        <input type="password" name="password"/>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <button type="submit" class="boton">Login</button>
    </form>
</body>
</html>