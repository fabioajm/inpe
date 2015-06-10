<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

<jsp:include page="../head.jsp">
	<jsp:param name="title" value="Meu carrinho" />
</jsp:include>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}

th {
	text-align: initial;
}
</style>

<body>

	<jsp:include page="../header.jsp" />

	<section class="conteudo">
		<c:if test="${not empty carrinho.produtos }">
			<table>
				<tr>
					<th>Produto</th>
					<th>Descrição</th>
					<th>Preço Unitario</th>
					<th>Quantidade</th>
					<th>Preço Total</th>
				</tr>
				<c:forEach items="${carrinho.produtos}" var="entry">
					<tr>
						<td><img src="/ProjetoFinal/produto/image?id=${entry.key.id}"
							class="imgMinun"></td>
						<td>${entry.key.nome}</td>
						<td>R$ ${entry.key.preco}</td>
						<td>${entry.value}</td>
						<td>R$ ${entry.key.preco * entry.value}</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>frete gratis</td>
					<td>R$ ${carrinho.total}</td>
					<td>Desconto R$ ${carrinho.desconto}</td>
					<td>Total R$ ${carrinho.totalAPagar}</td>
				</tr>
			</table>

			<form action="/carrinho/finalizar">
			<a href="#" onclick="$(this).closest('form').submit()" class="pagar_btn">Finalizar</a>
			</form>
		</c:if>
		<c:if test="${empty carrinho.produtos}">
			<h1>Seu carrinho esta vazio!</h1>
		</c:if>
	</section>

	<jsp:include page="../footer.jsp" />
</body>
</html>