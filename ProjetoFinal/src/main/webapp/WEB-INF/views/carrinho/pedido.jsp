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
	<div class="wrapper"> 
		<c:if test="${not empty carrinho.produtos }">
		<div class="tituloPagina">
					<h3>Dados do seu pedido!</h3>
				</div>
		<div id="tabelaCarrinho">
			<table>
				<tr>
	
					<th>Imagem</th>
					<th>Descrição</th>
					<th>Preço Unitario</th>
					<th>Quantidade</th>
					<th>Preço Total</th>
				</tr>
				<c:forEach items="${carrinho.produtos}" var="entry">
					<tr>
						<td class="image"><img src="/ProjetoFinal/produto/image?id=${entry.key.id}" class="imgMedium"></td>
						<td class="alinhaTopo descricao"><h2>${entry.key.nome}</h2>
							${entry.key.descricao } </td>
						<td class="alinhaMeio textoCentro">R$ ${entry.key.preco}</td>
						<td class="alinhaMeio textoCentro">${entry.value}</td>
						<td class="alinhaMeio textoCentro">R$ ${entry.key.preco * entry.value}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4"></td>
					<td class="alinhaMeio textoCentro">R$ ${carrinho.total}</td>
				</tr>
				<tr>
					<td colspan="4"></td>
					<td class="alinhaMeio textoCentro">R$ ${carrinho.desconto}</td>
				</tr>
				<tr>
					<td colspan="4"></td>
					<td class="alinhaMeio textoCentro">R$ ${carrinho.totalAPagar}</td>
				</tr>
			</table>
			</div>

		</c:if> 
		<c:if test="${empty carrinho.produtos}">
			<h1>Não existe pedido!</h1>
		</c:if> 
		</div>
	</section>

	<jsp:include page="../footer.jsp" />
</body>
</html>