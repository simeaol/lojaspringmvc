<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito
	mais-Loja</title>
	
<c:url value="/resources/css" var="cssPath"></c:url>	
<c:url value="/" var="contextPath" />
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css" >

<style type="text/css">
	body {
	padding-top: 0px;
}
</style>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build() }">Casa do Código</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="${s:mvcUrl('PC#show').build()}">Lista de Produtos</a></li>
        <li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li><a href="#">
    		<security:authentication property="principal" var="usuario"/>
    		Usuário: ${usuario.username }
    	</a></li>
    </ul>
    </div><!-- /.navbar-collapse -->
  </div>
</nav>
	<h1>${status}</h1>

<div class="container">
	<h1>Lista de produtos</h1>
	<table class="table table-striped">
		<tr>
			<th>Título</th>
			<th>Descirção</th>
			<th>Páginas</th>
		</tr>

		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>
					<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.nome}</a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>

	</table>
</div>
</body>
</html>