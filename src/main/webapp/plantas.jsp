<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vivario | Catálogo de plantas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<header>
    <h1>🌿 Vivario</h1>
    <p>Catálogo y cuidado de plantas</p>
</header>

<main>
    <c:if test="${param.ok == 1}">
        <div class="alerta ok">Planta guardada correctamente.</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alerta error"><c:out value="${error}"/></div>
    </c:if>

    <!-- GUARDAR datos -->
    <section class="tarjeta">
        <h2>Registrar nueva planta</h2>
        <form action="${pageContext.request.contextPath}/plantas" method="post" class="formulario">
            <label>Nombre común
                <input type="text" name="nombreComun" maxlength="80" required>
            </label>
            <label>Nombre científico
                <input type="text" name="nombreCient" maxlength="120" required>
            </label>
            <label>Tipo
                <select name="tipo" required>
                    <option>Interior</option>
                    <option>Exterior</option>
                    <option>Suculenta</option>
                    <option>Aromática</option>
                </select>
            </label>
            <label>Riego (cada cuántos días)
                <input type="number" name="riegoDias" min="1" max="60" required>
            </label>
            <label>Luz
                <select name="luz" required>
                    <option>Baja</option>
                    <option>Media</option>
                    <option>Alta</option>
                </select>
            </label>
            <label>Precio (MXN)
                <input type="number" name="precio" min="0" step="0.01" required>
            </label>
            <button type="submit">Guardar planta</button>
        </form>
    </section>

    <!-- TRAER datos -->
    <section class="tarjeta">
        <h2>Plantas registradas
            <span class="contador"><c:out value="${plantas.size()}"/></span>
        </h2>

        <c:choose>
            <c:when test="${empty plantas}">
                <p class="vacio">Aún no hay plantas registradas.</p>
            </c:when>
            <c:otherwise>
                <div class="tabla-scroll">
                <table>
                    <thead>
                        <tr>
                            <th>#</th><th>Planta</th><th>Tipo</th><th>Riego</th>
                            <th>Luz</th><th>Precio</th><th>Registro</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${plantas}">
                        <tr>
                            <td>${p.id}</td>
                            <td>
                                <strong><c:out value="${p.nombreComun}"/></strong><br>
                                <em><c:out value="${p.nombreCient}"/></em>
                            </td>
                            <td><span class="etiqueta"><c:out value="${p.tipo}"/></span></td>
                            <td>Cada ${p.riegoDias}
                                <c:choose>
                                    <c:when test="${p.riegoDias == 1}">día</c:when>
                                    <c:otherwise>días</c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${p.luz}"/></td>
                            <td><fmt:formatNumber value="${p.precio}" type="currency" currencySymbol="$"/></td>
                            <td><fmt:formatDate value="${p.fechaRegistro}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</main>

<footer>Vivario · Proyecto JSP + JSTL + MySQL</footer>
</body>
</html>
