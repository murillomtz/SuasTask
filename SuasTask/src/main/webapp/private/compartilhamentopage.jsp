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

		<h1>TAREFAS COMPARTILHADA COM VOCE</h1>
		<hr>
		<div>
			<table class="table table-striped ">
				<tr>
					<th>Titulo</th>
					<th>Descrição</th>
					<th>Concluida</th>
					<th colspan="1">Numero de Tarefas: ${tarefas.size()}</th>
				</tr>
				<c:forEach var="task" items="${tarefas}">
					<tr>
						<td>${task.titulo}</td>
						<td>${task.descricao}</td>
						<td>${task.concluida ? 'SIM' : 'NÃO'}</td>

					</tr>
				</c:forEach>
			</table>

		</div>

	</div>

	<c:import url="../scripts.jsp" />

</body>
</html>