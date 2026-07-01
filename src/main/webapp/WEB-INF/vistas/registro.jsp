<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - Pinturas</title>
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body class="formulario-estrecho">
    <div class="superior">
        <a href="/login">Login</a>
        <a href="/registro">Registro</a>
    </div>
    <h1>Pinturas</h1>
    <h2>Registro</h2>

    <form:form action="/registro" method="post" modelAttribute="usuario">
        <label>Nombre</label>
        <form:input path="nombre"/>
        <form:errors path="nombre" cssClass="error" element="div"/>

        <label>Apellido</label>
        <form:input path="apellido"/>
        <form:errors path="apellido" cssClass="error" element="div"/>

        <label>Correo</label>
        <form:input path="correo"/>
        <form:errors path="correo" cssClass="error" element="div"/>

        <label>Contraseña</label>
        <form:password path="password"/>
        <form:errors path="password" cssClass="error" element="div"/>

        <label>Confirmar contraseña</label>
        <form:password path="confirmarPassword"/>
        <form:errors path="confirmarPassword" cssClass="error" element="div"/>

        <button type="submit" class="boton">Registrarse</button>
    </form:form>
</body>
</html>