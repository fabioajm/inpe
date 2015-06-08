<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">

	<jsp:include page="../head.jsp" />

<body>

	<jsp:include page="../header.jsp" />

	<section class="conteudo">
		<sf:form method="POST" action="save" enctype="multipart/form-data">
				
		        Nome: <input type="text" name="nome" value="${produto.nome }"><br />
		        Descri��o: <textarea name="descricao" rows="4" cols="50" >${produto.descricao }</textarea><br />
				Pre�o: <input type="text" name="preco"  value="${produto.preco }"/>  <br />  
				Qtd:
					<select name="qtd" >
					<c:forEach begin="0" end="20" var="valor">
						<option value="${valor }" ${ qtd == valor ? "selected='selected'" :"" }">${valor }</option>
					</c:forEach>
					</select>     <br /> 
		        Imagem: <input type="file" name="image"><br />  <br /> 
		        <input type="hidden" name="id" value="${produto.id }">
		        <input type="submit" value="Salvar">
		    </sf:form>
		    
	</section>   
	 
	<jsp:include page="../footer.jsp" />
</body>
</html>