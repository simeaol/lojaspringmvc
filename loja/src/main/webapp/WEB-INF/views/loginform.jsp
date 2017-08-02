<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito
	mais-Loja</title>

<c:url value="/resources/css" var="cssPath"></c:url>	
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" >
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" >

<style type="text/css">
	body {
	padding-top: 60px 0px;
}
</style>
</head>
<body>

	<div class="container">	
	<h1>Página do Login da Loja</h1>
	<form:form servletRelativeAction="/login" method="POST">
		<div class="form-group">
			<label>Email:</label>
			<input type="text" name="username" class="form-control" />
		</div>
			<div class="form-group">
				<label>Senha:</label> <input type="text" name="password"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Entrar</button>
	</form:form>
	</div>

</body>
</html>