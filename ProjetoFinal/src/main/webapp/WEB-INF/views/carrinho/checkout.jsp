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

	<jsp:include page="../header.jsp" />

	<section class="conteudo">

		<form method="POST" action="<c:url value="/carrinho/checkout"/>" >
			<div class="wrapper ">
				<div class="tituloPagina">
					<h3>Finalizando sua Compra</h3>
				</div>
				<form action="">
				<div id="checkout">
					<div class="colunas">
						<div class="tituloCaixa">
							<h4>Nome e Endereço</h4>
						</div>
						<div>
							
								<div id="tabelaNome">
									<table>
										<tr>
											<td>Nome</td>
											<td><input class="input80" type="text" /></td>
											<td>Sobrenome</td>
											<td><input class="input80" type="text" /></td>
										</tr>
										<tr>
											<td>Endereço</td>
											<td colspan="3"><input class="input90" type="text" /></td>
										</tr>
										</table>
								</div>
							
						</div>
					</div>
					<div class="colunas">
						<div id="formaEntrega">
							<div class="tituloCaixa">
								<h4>Forma de Entrega</h4>
							</div>
							<div id="tabelaFormaEntrega">
									<table>
										<tr>
											<td>Sedex</td>
											<td><input type="radio" /></td>
											<td>Pac</td>
											<td><input type="radio" /></td>
										</tr>
										
									</table>
							</div>
						</div>
						<div id="formaPagamento">
							<div class="tituloCaixa">
								<h4>Forma de Pagamento</h4>
							</div>
							<div id="tabelaPagamento">
									<table>
										<tr>
											<td>Selecione a Forma de Pagamento</td>
											<td>
											
												<select name="tipo" onchange="this.form.submit()">
													<option value="CARTAO_CREDITO">Cartão de Crédito</option>
													<option value="CARTAO_DEBITO">Cartão de Débito</option>
													<option value="BOLETO">Boleto Bancario</option>
												</select>
											
											</td>
									</table>
								</div>
						</div>
					</div>
					<div class="colunas">
						<div class="tituloCaixa">
							<h4>Revise sua compra</h4>
						</div>
						<div id="tabelaRevise">
									<table>
										<tr>
											<th>Produto</th>
											<th>Quant</th>
											<th>Valor</th>
										</tr>
										<c:forEach items="${carrinho.produtos}" var="entry">
										<tr>
											<td>${entry.key.nome}</td>
											<td class="textoCentro" >${entry.value}</td>
											<td class="textoCentro" >R$ ${entry.key.preco * entry.value}</td>
										</tr>
										</c:forEach>
									</table>
									<div id="tabelaCalculoFinal">
									<table>
										<tr>
										
											<td><p>Subtotal</p></td>
											<td>R$ ${carrinho.total}</td>
										</tr>
										<tr>
										
											<td><p>Desconto</p></td>
											<td>R$ ${carrinho.desconto}</td>
										</tr>
										<tr>
										
											<td><p>Total Final</p></td>
											<td>R$ ${carrinho.totalAPagar}</td>
										</tr>
									</table>
									</div>
								</div>
					</div>



					<div id="clearBoth"></div>
				</div>
				<div class=""><input type="button" class="pagar_btn" value="Finalizar Compra"> </div>
				</form>
			</div>
		</form>
	</section>

	<jsp:include page="../footer.jsp" />
</body>
</html>