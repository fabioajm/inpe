<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

	<jsp:include page="head.jsp" />

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
				
		        Email: <input  type="email"  name="login" ><br />
				Senha: <input type="password" name="senha" />  <br />      
		        <input type="submit" value="Enviar">
		    </form>
		    </fieldset>
		    <fieldset>
				<legend>Novo Usuario</legend>
		    <form method="POST" action="<c:url value="/usuario/save"/>" >
				
		        Nome: <input type="text" name="nome"><br />
		        Email: <input  type="email"  name="login" ><br />
				Senha: <input type="password" name="senha" />  <br />      
		        <input type="submit" value="Salvar">
		    </form>
		    </fieldset>
		    
	</section>   
	 
	<jsp:include page="footer.jsp" />
</body>
</html>