<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

	<jsp:include page="head.jsp" >
		<jsp:param name="title" value="Login"/>
	</jsp:include>
<body>
<fieldset>
<legend></legend>
</fieldset>
	<jsp:include page="header.jsp" />

	<section class="conteudo">
		<div class="wrapper">
			<label style="color: red">${mensagem }</label>
			<div id="formCadastros">
				<div class="formLogin">
					<h4 class="alinhaTitulo">Logar</h4>
					<form method="POST" action="<c:url value="/efetuarLogin"/>">

						<div class="alinhaForm">
							<label>Email</label> <label><input type="email"
								name="login" required="required"></label>
						</div>
						<div class="alinhaForm">
							<label>Senha</label> <label><input type="password"
								name="senha" required="required" /></label>
						</div>
						<div class="alinhaForm alinhaBt">
							<input class="pagar_btn" type="submit" id="enviar" value="Enviar">
						</div>
					</form>
				</div>
				<div class="formLoginNovo">

					<h4 class="alinhaTitulo">Novo Usuário</h4>
					<form method="POST" action="<c:url value="/usuario/save"/>">

						<div class="alinhaForm">
							<label>Nome</label> <label><input type="text" name="nome"
								required="required"></label>
						</div>
						<div class="alinhaForm">
							<label>Email</label> <label><input type="email"
								name="login" required="required"></label>
						</div>
						<div class="alinhaForm">
							<label>Senha</label> <label><input type="password"
								name="senha" required="required" /></label>
						</div>
						<div class="alinhaForm alinhaBt">
							<input class="pagar_btn" type="submit" id="salvar" value="Cadastrar">
						</div>
					</form>
				</div>
				<div id="clearBoth"></div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>