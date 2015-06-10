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

		<sf:form method="POST" action="save" enctype="multipart/form-data">
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
											<td><select>
												<option>Cartão de Credito</option>
												<option>Cartão de Debito</option>
												<option>Boleto Bancario</option>
											</select></td>
											
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
											<th>Produto</td>
											<th>Quant</td>
											<th>Valor</td>
										</tr>
										<tr>
											<td>Matrix Reloaded</td>
											<td class="textoCentro" >2</td>
											<td class="textoCentro" >$R 12,50</td>
										</tr>
									</table>
									<div id="tabelaCalculoFinal">
									<table>
										<tr>
										
											<td><p>Subtotal</p></td>
											<td>R$12,50</td>
										</tr>
										<tr>
										
											<td><p>Envio</p></td>
											<td>7,50</td>
										</tr>
										<tr>
										
											<td><p>Total Final</p></td>
											<td>R$20,00</td>
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
		</sf:form>
	</section>

	<jsp:include page="../footer.jsp" />
</body>
</html>