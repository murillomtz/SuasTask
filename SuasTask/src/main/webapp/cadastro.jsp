<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>


<jsp:include page="header.jsp"></jsp:include>
<jsp:useBean id="tarefas" class="java.util.ArrayList" scope="request" />
</head>
<body>
	<c:import url="/navbarhome.jsp" />



	<h1>
		<strong>Pagina de cadastro</strong>
	</h1>

	<hr />
	<div class="container">

		<form method="post" action="/cadastro">
			<input type="hidden" name="id" value="${usuario.id}" />
			<div class="form-group">
				<label for="login">Login</label> <input type="text"
					class="form-control" value="${usuario.login}" id="login"
					name="login" required="required" />
			</div>

			<div class="form-group">
				<label for="nome">Nome</label> <input type="text"
					class="form-control" value="${usuario.nome}" id="nome" name="nome"
					required="required" />
			</div>

			<div class="form-group">
				<label for="email">E-mail</label> <input type="email"
					class="form-control" required="required" value="${usuario.email}"
					id="email" name="email" />
			</div>
			<div class="form-group">
				<label for="senha">Senha</label> <input type="password"
					class="form-control" value="${usuario.senha}" id="senha"
					name="senha" />
			</div>

			<button type="submit" class="btn btn-primary">Salvar</button>
		</form>
	</div>
</body>
</html>
