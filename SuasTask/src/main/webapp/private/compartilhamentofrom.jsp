<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="userCompartilhado" class="java.util.ArrayList"
	scope="request" />

</head>
<body>

	<h1>USUARIOS JÁ PERTENCENTES</h1>

	<div>

		<table class="table table-striped ">
			<tr>
				<th>Login</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Ativo</th>
				<th colspan="2">Quantidade de usuários:
					${userCompartilhado.size()}</th>

			</tr>
			<c:forEach var="user" items="${userCompartilhado}">
				<tr>
					<td>${user.login}</td>
					<td>${user.nome}</td>
					<td>${user.email}</td>
					<td>${user.ativo ? 'SIM' : 'NÃO'}</td>
					<td><a
						href="/remove/compartilhamento?out=${user.id}&outtask=${tarefa.id}">REMOVER</a></td>


				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>