<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<c:import url="../header.jsp" />
<jsp:useBean id="usuarios" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="tarefax" class="java.util.ArrayList" scope="request" />
</head>
<body>
	<c:import url="/private/navbaruser.jsp" />
	
	<input type="hidden" name="id" value="${tarefa.id}">
	<div class="container">

		<div>
			<c:import url="/private/compartilhamentofrom.jsp" />
		</div>
		<hr>
		<h1>TABELA DE USUARIOS</h1>
		<div>

			<table class="table table-striped ">
				<tr>
					<th>Login</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Ativo</th>
					<th colspan="2">Quantidade de usuários: ${usuarios.size()}</th>

				</tr>
				<c:forEach var="user" items="${usuarios}">
					<tr>
						<td>${user.login}</td>
						<td>${user.nome}</td>
						<td>${user.email}</td>
						<td>${user.ativo ? 'SIM' : 'NÃO'}</td>
						<td><a href="/compartilhar/add?page=${user.id}&idtask=${tarefa.id}">ADD</a></td>


					</tr>
				</c:forEach>
			</table>

		</div>

	</div>

	<c:import url="../scripts.jsp" />

</body>
</html>