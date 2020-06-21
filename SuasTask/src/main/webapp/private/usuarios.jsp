<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
   	<c:import url="../header.jsp" />
    <jsp:useBean id="usuarios" class="java.util.ArrayList" scope="request" />
</head>
<body>
<c:import url="/private/navbar.jsp" />

<div class="container">

    <div>
        <c:import url="/private/userform.jsp" />
    </div>

    <hr>

    <div>

        <table class="table table-striped ">
            <tr>
                <th>Login</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ativo</th>
                <th colspan="2">Quantidade de usuários: ${usuarios.size()} </th>

            </tr>
            <c:forEach var="user" items="${usuarios}" >
                <tr>
                    <td>${user.login}</td>
                    <td>${user.nome}</td>
                    <td>${user.email}</td>
                    <td>${user.ativo ? 'SIM' : 'NÃO'}</td>
                    <td><a href="/usuarios/excluir?page=${user.id}" >EXCLUIR</a></td>
                    <td><a href="/usuarios/editarUser?page=${user.id}" >ALTERAR</a></td>

                </tr>
            </c:forEach>
        </table>

    </div>

</div>

<c:import url="../scripts.jsp" />

</body>
</html>