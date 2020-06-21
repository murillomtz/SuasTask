<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../header.jsp" />
<jsp:useBean id="tarefas" class="java.util.ArrayList" scope="request" />

</head>
<body>
	<c:import url="/private/navbaruser.jsp" />

	<div class="container">

		<div>
			<c:import url="/private/tarefaform.jsp" />
		</div>
		<hr>

		<div>

			<table class="table table-striped ">
				<!-- Preciso recuperar o IDdo usuario -->

				<tr>
					<th>Titulo</th>
					<th>Descrição</th>
					<th>Concluida</th>
					<th colspan="3">Numero de Tarefas: ${tarefas.size()}</th>

				</tr>
				<c:forEach var="task" items="${tarefas}">
					<tr>
						<td>${task.titulo}</td>
						<td>${task.descricao}</td>
						<td>${task.concluida ? 'SIM' : 'NÃO'}</td>
						<td><a href="/excluir?usuariopage=${task.id}">EXCLUIR</a></td>
						<td><a href="/editartask?usuariopage=${task.id}">ALTERAR</a></td>
						<td><a href="/compartilhamento?usuariopage=${task.id}">Compartilhar</a></td>
					</tr>
				</c:forEach>
			</table>
			

		</div>

	</div>

	<c:import url="../scripts.jsp" />

</body>
</html>