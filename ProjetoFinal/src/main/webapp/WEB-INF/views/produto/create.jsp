<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

	<jsp:include page="../head.jsp" >
		<jsp:param name="title" value="Produto"/>
	</jsp:include>

<body>

	<jsp:include page="../adm/header.jsp" />

	<section class="conteudo">
	
		<sf:form method="POST" action="save" enctype="multipart/form-data">
			<div class="wrapper">
				<div class="tituloPagina">
				<c:if test="${produto == null}">
					<h3>Cadastro - Produtos</h3>
				</c:if>
				<c:if test="${produto != null}">
					<h3>Atualizar - Produtos</h3>
				</c:if>
				</div>
				<div id="cadastro">
					<table>
						<tr>
							<td>Nome</td>
							<td><input type="text" name="nome" value="${produto.nome }"></td>
						</tr>
						<tr>
							<td class="alinhaTopo">Descrição</td>
							<td><textarea name="descricao" rows="4" cols="50">${produto.descricao }</textarea></td>
						</tr>
						<tr>
							<td>Preço</td>
							<td><input type="text" name="preco" value="${produto.preco }" /></td>
						</tr>
						<tr>
							<td>Quantidade</td>
							<td><select name="qtd">
									<c:forEach begin="0" end="20" var="valor">
										<option value="${valor }" ${ qtd == valor ? "selected='selected'" :"" }>${valor }</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Imagem</td>
							<td><input type="file" name="image"></td>
						</tr>
					</table>
					 <input type="hidden" name="id" value="${produto.id }">
					<input class="pagar_btn" type="submit" value="Salvar">
				</div>
			</div>
		</sf:form>
	</section>   
	 
	<jsp:include page="../footer.jsp" />
</body>
</html>