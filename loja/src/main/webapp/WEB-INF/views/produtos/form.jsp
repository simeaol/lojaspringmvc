<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito
	mais-Loja</title>
</head>
<body>
	<form:form action="${s:mvcUrl('PC#save').build() }" method="POST" commandName="produto">
		<div>
			<label>Nome:</label> <input type="text" name="nome" />
			<form:errors path="nome" />
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Páginas</label> <input type="text" name="paginas">
			<form:errors path="paginas" />
		</div>
		<div>
			<c:forEach items="${tipos}" var="precoTipo" varStatus="status">
				<div>
					<label>${precoTipo}</label> <input type="text"
						name="precos[${status.index}].valor" /> <input type="hidden"
						name="precos[${status.index}].tipo" value="${precoTipo}" />
				</div>

			</c:forEach>
		</div>

		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>