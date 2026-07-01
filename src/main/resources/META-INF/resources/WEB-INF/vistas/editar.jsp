<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar pintura - Pinturas</title>
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body>
    <jsp:include page="fragmentos/menu.jsp"/>
    <h2>Editar pintura</h2>

    <div class="formulario-estrecho">
        <form:form action="/pinturas/${pintura.id}/editar" method="post" modelAttribute="pintura">
            <label>Título</label>
            <form:input path="titulo"/>
            <form:errors path="titulo" cssClass="error" element="div"/>

            <label>Año</label>
            <form:input path="anio" type="number"/>
            <form:errors path="anio" cssClass="error" element="div"/>

            <label>Descripción</label>
            <form:textarea path="descripcion" rows="3"/>
            <form:errors path="descripcion" cssClass="error" element="div"/>

            <label>URL a imagen</label>
            <form:input path="urlImagen"/>
            <form:errors path="urlImagen" cssClass="error" element="div"/>

            <label>Cantidad</label>
            <form:input path="cantidad" type="number"/>
            <form:errors path="cantidad" cssClass="error" element="div"/>

            <label>Precio</label>
            <form:input path="precio" type="number" step="0.01"/>
            <form:errors path="precio" cssClass="error" element="div"/>

            <div class="acciones">
                <button type="submit" class="boton">Editar</button>
            </div>
        </form:form>

        <c:if test="${pintura.creador.id == usuario.id}">
            <form action="/pinturas/${pintura.id}/eliminar" method="post">
                <button type="submit" class="boton boton-eliminar">Eliminar</button>
            </form>
        </c:if>
    </div>
</body>
</html>