<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito mais -
	Loja</title>
</head>
<body>
	<form action="/loja/produtos" method="POST">
		<div>
			<label>Nome:</label> <input type="text" name="nome" />
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="5" cols="5" name="descricao"></textarea>
		</div>
		<div>
			<label>Páginas</label> <input type="text" name="paginas">
		</div>
		<div>
			<c:forEach items="${tipos}" var="precoTipo" varStatus="status">
				<div>
					<label>${precoTipo}</label>
					<input type="text" name="precos[${status.index}].valor" />
					<input type="hidden" name="precos[${status.index}].tipo" value="${precoTipo}" />
				</div>

			</c:forEach>
		</div>

		<button type="submit">Cadastrar</button>
	</form>

</body>
</html>