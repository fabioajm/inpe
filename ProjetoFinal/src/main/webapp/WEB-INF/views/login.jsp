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
		<label style="color:red">${mensagem }</label>
		<fieldset>
			<legend>Logar</legend>
			<form method="POST" action="<c:url value="/efetuarLogin"/>" >
				
		        Email: <input  type="email"  name="login" required="required"><br />
				Senha: <input type="password" name="senha" required="required"/>  <br />      
		        <input type="submit" id="enviar" value="Enviar">
		    </form>
		    </fieldset>
		    <fieldset>
				<legend>Novo Usuario</legend>
		    <form method="POST" action="<c:url value="/usuario/save"/>" >
				
		        Nome: <input type="text" name="nome" required="required"><br />
		        Email: <input  type="email"  name="login" required="required"><br />
				Senha: <input type="password" name="senha" required="required"/>  <br />      
		        <input type="submit" id="salvar" value="Salvar" >
		    </form>
		    </fieldset>
		    
	</section>   
	 
	<jsp:include page="footer.jsp" />
</body>
</html>