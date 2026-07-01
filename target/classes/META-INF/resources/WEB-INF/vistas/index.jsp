<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pinturas</title>
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body>
    <jsp:include page="fragmentos/menu.jsp"/>
    <h2>Bienvenido de vuelta ${usuario.nombre} ${usuario.apellido}</h2>

    <c:forEach var="pintura" items="${pinturas}">
        <div class="tarjeta">
            <img src="${pintura.urlImagen}" alt="${pintura.titulo}">
            <div class="info">
                <a class="detalle" href="/pinturas/${pintura.id}">${pintura.titulo}</a>
                <table>
                    <tr><td>Autor</td><td>${pintura.creador.nombre} ${pintura.creador.apellido}</td></tr>
                    <tr><td>Año</td><td>${pintura.anio}</td></tr>
                    <tr><td>Precio</td><td>$ <fmt:formatNumber value="${pintura.precio}" minFractionDigits="2"/></td></tr>
                </table>
            </div>
            <c:if test="${pintura.creador.id == usuario.id}">
                <a class="editar" href="/pinturas/${pintura.id}/editar" title="Editar">✏️</a>
            </c:if>
        </div>
    </c:forEach>
</body>
</html>