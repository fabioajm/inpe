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
			<th>Title</th>
			<th>Descrição?</th>
			<th>Data de finalização</th>
			<th>ação</th>
		</tr>
		<c:forEach items="${movies}" var="movie">
			<tr>
				<td>${movie.id}</td>
				<td>${movie.title}</td>
				<td>${movie.description }</td>
				<td><img src="/ProjetoFinal/movie/image?id=${movie.id}">
				</td>
				<td><a href="remove?id=${movie.id}">remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>