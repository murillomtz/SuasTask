<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<form method="post" action="/salvar">

		<input type="hidden" name="id" value="${tarefa.id}"> <input
			type="hidden" name="id_user" value="${usuario.id}">
		<div class="form-group">
			<label for="titulo">Titulo</label> <input type="text"
				class="form-control" value="${tarefa.titulo}" id="titulo"
				name="titulo" required>
		</div>
		<div class="form-group">
			<label for="descricao">Descrição</label>
			<textarea class="form-control" id="descricao" name="descricao"
				rows="3">${tarefa.descricao}</textarea>
		</div>
		<div class="form-group form-check">
			<input type="checkbox" class="form-check-input"
				<c:if test="${tarefa.concluida}">checked="checked"</c:if>
				id="concluida" name="concluida"> <label
				class="form-check-label" for="concluida">Concluída</label>
		</div>
		<button type="submit" class="btn btn-primary">Salvar</button>

	</form>
</body>
</html>