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

	<jsp:include page="../header.jsp" />

	<section class="conteudo">
		<sf:form method="POST" action="save" enctype="multipart/form-data">
				
		        Nome: <input type="text" name="nome"><br />
		        Descrição: <textarea name="descricao" rows="4" cols="50" ></textarea><br />
				Preço: <input type="text" name="preco" />  <br />  
				Qtd:
					<select name="qtd" >
					<c:forEach begin="1" end="20" var="valor">
						<option value="${valor }">${valor }</option>
					</c:forEach>
					</select>     
		        Imagem: <input type="file" name="image"><br />  <br /> 
		        <input type="submit" value="Salvar">
		    </sf:form>
		    
	</section>   
	 
	<jsp:include page="../footer.jsp" />
</body>
</html>