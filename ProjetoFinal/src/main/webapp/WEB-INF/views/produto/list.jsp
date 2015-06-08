<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies</title>
</head>
<body>
	<h2>Olá mundo com Spring MVC!</h2>

	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Preço</th>
			<th>Imagem</th>
			<th>ação</th>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.id}</td>
				<td>${produto.nome}</td>
				<td>${produto.descricao }</td>
				<td>${produto.preco }</td>
				<td><img src="/ProjetoFinal/produto/image?id=${produto.id}">
				</td>
				<td><a href="remove?id=${produto.id}">remover</a><br/>
				<a href="update?id=${produto.id}">atualizar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>