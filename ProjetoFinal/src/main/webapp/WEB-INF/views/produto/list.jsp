<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

<jsp:include page="../head.jsp">
	<jsp:param name="title" value="Produto" />
</jsp:include>
<body>

	<jsp:include page="../adm/header.jsp" />

	<section class="conteudo">
		<div class="wrapper">
			<table>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Descri��o</th>
					<th>Pre�o</th>
					<th>Imagem</th>
					<th>a��o</th>
				</tr>
				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td>${produto.id}</td>
						<td>${produto.nome}</td>
						<td>${produto.descricao }</td>
						<td>${produto.preco }</td>
						<td><img src="/ProjetoFinal/produto/image?id=${produto.id}">
						</td>
						<td><a href="<c:url value="/produto/remove?id=${produto.id}" />"><h3>remover</h3></a><br /> <a
							href="<c:url value="/produto/update?id=${produto.id}" />"><h3>atualizar</h3></a></td>
							
							
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>

	<jsp:include page="../footer.jsp" />
</body>
</html>
