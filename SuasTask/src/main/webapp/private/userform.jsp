<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<form method="post" action="/usuarios/salvar"  >

    <input type="hidden" name="id" value="${usuario.id}" >
    <div class="form-group">
        <label for="login">Login</label>
        <input type="text" class="form-control" value="${usuario.login}"
        id="login" name="login" required >
    </div>
    <div class="form-group">
        <label for="nome">Nome</label>
        <input type="text" class="form-control" required value="${usuario.nome}"
               id="nome" name="nome">
    </div>
    <div class="form-group">
        <label for="email">E-mail</label>
        <input type="email" class="form-control" required value="${usuario.email}"
               id="email" name="email">
    </div>
    <div class="form-group">
        <label for="senha">Senha</label>
        <input type="password" class="form-control" required value="${usuario.senha}"
               id="senha" name="senha">
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" <c:if test="${usuario.ativo}">checked="checked"</c:if>
               id="ativo" name="ativo" >
        <label class="form-check-label" for="ativo">Ativo</label>
    </div>
    <button type="submit" class="btn btn-primary" >Salvar</button>

</form>
</body>
</html>