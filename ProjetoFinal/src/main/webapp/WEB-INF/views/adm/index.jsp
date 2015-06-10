<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">
<head>
	<jsp:include page="../head.jsp" >
		<jsp:param name="title" value="Index Administração"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="header.jsp" />

	<section class="listings" id="listings">
		<div class="wrapper">
			<ul class="properties_list">
			
				<c:forEach items="${estoques}" var="estoque">
				
					<li>
							<a href="#">
								<img src="<c:url value="/produto/image?id=${estoque.produto.id}"/>" alt="" title="" class="property_img"/>
							</a>
							<span class="price">R$ ${estoque.produto.preco}</span>
							<div class="property_details">
								<h1>
									<a href="#">${estoque.produto.nome}</a>
								</h1>
								<h2>${estoque.produto.descricao } </h2>
								<a href="remove?id=${estoque.produto.id}"><h2>remover</h2></a>
								Qauntidade em estoque: ${estoque.quantidade }
								<form action="<c:url value="/produto/estoque/adicionar"/>">
									<input type="hidden" value="${estoque.produto.id}" name="id" />
									
									Adicionar no estoque Qtd:
									<select name="qtd" >
									<c:forEach begin="1" end="10" var="valor">
										<option value="${valor }">${valor }</option>
									</c:forEach>
									</select>
									<input type="submit" value="adicionar">
								</form>
								
							</div>
						</li>
				</c:forEach>
								
			</ul>
			<div class="more_listing">
				<a href="#" class="more_listing_btn">Ver mais resultados</a>
			</div>
		</div>
	</section>	<!--  end listing section  -->

	<jsp:include page="../footer.jsp" />
	
</body>
</html>