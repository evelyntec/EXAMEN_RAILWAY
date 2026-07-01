<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${pintura.titulo} - Pinturas</title>
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body>
    <jsp:include page="fragmentos/menu.jsp"/>
    <h1>${pintura.titulo}</h1>

    <div class="fila">
        <img src="${pintura.urlImagen}" alt="${pintura.titulo}">
        <div>
            <p><strong>Autor</strong><br>${pintura.creador.nombre} ${pintura.creador.apellido}</p>
            <p><strong>Descripción</strong><br>${pintura.descripcion}</p>
            <p><strong>Precio</strong><br>$ <fmt:formatNumber value="${pintura.precio}" minFractionDigits="2"/></p>
            <p><strong>Cantidad en inventario</strong><br>${pintura.cantidad}</p>

            <c:choose>
                <c:when test="${pintura.cantidad > 0}">
                    <form action="/pinturas/${pintura.id}/comprar" method="post">
                        <button type="submit" class="boton">Comprar</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p class="agotado">¡Agotado!</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <h3>Usuarios que han comprado esta pintura</h3>
    <ul>
        <c:forEach var="comprador" items="${compradores}">
            <li>${comprador.nombre} ${comprador.apellido}</li>
        </c:forEach>
    </ul>
</body>
</html>