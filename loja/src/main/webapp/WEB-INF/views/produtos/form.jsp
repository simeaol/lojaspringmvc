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
</head>
<body>
	<form:form action="${s:mvcUrl('PC#save').build() }" method="POST"
		commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Nome:</label>
			<form:input path="nome" />
			<form:errors path="nome" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20" />
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input path="data" />
			<form:errors path="data" />
		</div>
		<div>
			<c:forEach items="${tipos}" var="precoTipo" varStatus="status">
				<div>
					<label>${precoTipo}</label>
					<form:input path="precos[${status.index}].valor" />
					<form:hidden path="precos[${status.index}].tipo" value="${precoTipo}" />
				</div>
			</c:forEach>
		</div>
		<div>
			<label>Sumário</label>
			<input name="sumario" type="file" />
		</div>
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>