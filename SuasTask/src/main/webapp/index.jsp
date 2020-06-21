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
		<strong>Pagina de Login</strong>
	</h1>
	<hr>

	<div class="container">

		<div class="content">
			<!--FORMULÁRIO DE LOGIN-->
			<div id="login">

				<form action="/login" method="post">

					<div class="form-group">
						<label>Login</label> <input name="user_login" class="form-control"
							required="required" type="text" placeholder="ex. 000123321" />
					</div>

					<div class="form-group">
						<label>Senha</label> <input name="user_senha" class="form-control"
							required="required" type="password" placeholder="ex. senha" />
					</div>

					
					<br>
					<div>
						<c:out value="${erro}"></c:out>
					</div>
					<p>
						<input type="submit" value="Logar" class="btn btn-primary" />

					</p>



				</form>

				<a href="/cadastro.jsp"><input type="submit" value="Cadastrar"
					class="btn btn-primary" /></a>
			</div>

			<p></p>
		</div>
	</div>

	<jsp:include page="scripts.jsp"></jsp:include>

</body>
</html>