<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">
<head>
	<jsp:include page="../head.jsp" >
		<jsp:param name="title" value="Index Administração"/>
	</jsp:include>
</head>
<body>

	<section class="hero">
		<header>
			<div class="wrapper">
			<div align="right"  style="padding:10px">
				<c:if test="${usuarioAdm != null }">
					<label style="color:#fff;">Olá, ${usuarioAdm.nome}</label> 
				</c:if>
				</div>
				<a href="#"><img src="img/logo.png" class="logo" alt="" title=""/></a>
				<a href="#" class="hamburger"></a>
				<nav>
					<ul>
						<li><a href="<c:url value="/produto/create"/>">Cadastrar Produto</a></li>
						<li><a href="<c:url value="/produto/list"/>">Listar Produtos</a></li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Contato</a></li>
					</ul>
					<c:if test="${usuarioAdm != null }">
						<a href="<c:url value="/adm/logout"/>" class="login_btn">Logout</a>
					</c:if>
				</nav>
				
			</div>
		</header><!--  end header section  -->

			<section class="caption">
				<h2 class="caption">Encontre filmes e livros</h2>
				<h3 class="properties">Filmes - Livros</h3>
			</section>
	</section><!--  end hero section  -->


	<section class="search">
		<div class="wrapper">
			<form action="#" method="post">
				<input type="text" id="search" name="search" placeholder="O que você procura?"  autocomplete="off"/>
				<input type="submit" id="submit_search" name="submit_search"/>
			</form>
			<a href="#" class="advanced_search_icon" id="advanced_search_btn"></a>
		</div>

		<div class="advanced_search">
			<div class="wrapper">
				<span class="arrow"></span>
				<form action="#" method="post">
					<div class="search_fields">
						<input type="text" class="float" id="check_in_date" name="check_in_date" placeholder="Check In Date"  autocomplete="off">

						<hr class="field_sep float"/>

						<input type="text" class="float" id="check_out_date" name="check_out_date" placeholder="Check Out Date"  autocomplete="off">
					</div>
					<div class="search_fields">
						<input type="text" class="float" id="min_price" name="min_price" placeholder="Min. Price"  autocomplete="off">

						<hr class="field_sep float"/>

						<input type="text" class="float" id="max_price" name="max_price" placeholder="Max. price"  autocomplete="off">
					</div>
					<input type="text" id="keywords" name="keywords" placeholder="Keywords"  autocomplete="off">
					<input type="submit" id="submit_search" name="submit_search"/>
				</form>
			</div>
		</div><!--  end advanced search section  -->
	</section><!--  end search section  -->


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