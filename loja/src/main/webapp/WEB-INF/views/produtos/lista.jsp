<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito
	mais-Loja</title>
</head>
<body>

	<table>
		<tr>
			<td>Título</td>
			<td>Descirção</td>
			<td>Páginas</td>
		</tr>

		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.nome}</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>