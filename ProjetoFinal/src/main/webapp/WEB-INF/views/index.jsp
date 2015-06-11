<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">
<head>
	<jsp:include page="head.jsp" >
		<jsp:param name="title" value="Index"/>
	</jsp:include>
</head>
<body>

	<section class="hero">
		<header>
			<div class="wrapper">
			<div align="right"  style="padding:10px">
				<c:if test="${usuario != null }">
					<label style="color:#fff;">Olá, ${usuario.nome}</label> 
				</c:if>
				</div>
				<a href="#"><img src="img/logo.png" class="logo" alt="" title=""/></a>
				<a href="#" class="hamburger"></a>
				<nav>
					<ul>
						<li><a href="#listings">Compre</a></li>
						<li><a href="#">Alugue</a></li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Contato</a></li>
					</ul>
					<c:if test="${usuario == null }">
						<a href="<c:url value="/login"/>" id="login"  class="login_btn">Login</a>
					</c:if>
					<c:if test="${usuario != null }">
						<a href="<c:url value="/logout"/>" class="login_btn">Logout</a>
					</c:if>
					<c:if test="${carrinho != null }">
						<a href="<c:url value="/carrinho/carrinho"/>" id="meucarrinho" class="login_btn">Meu carrinho (${carrinho.quantidadeProdutos})</a>
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
		<div class="wrapper"><label style="color: red">${mensagem }</label>
			<ul class="properties_list">
			
				<c:forEach items="${estoques}" var="estoque" varStatus="status">
				
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
								
								<form action="<c:url value="/carrinho/adicionar"/>">
									<input type="hidden" value="${estoque.produto.id}" name="id" />
									Qtd:
									<select name="qtd" id="qtd${estoque.produto.id}" >
									<c:forEach begin="1" end="${estoque.quantidade}" var="valor">
										<option value="${valor }">${valor }</option>
									</c:forEach>
									</select>
									<input type="submit" id="adicionar${estoque.produto.id}" value="adicionar">
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

	<c:if test="${usuario != null and ! empty usuario.preferencias }">
		<section id="preferencias" class="listings">

			<div class="wrapper">
				<h1>Últimos vistos</h1>
			<ul class="properties_list">
					<c:forEach items="${usuario.preferencias}" var="preferencia"
						varStatus="status">
						<li><a href="#"> <img class="imgMedium"
								src="<c:url value="/produto/image?id=${preferencia.produto.id}"/>" alt=""
								title="" class="property_img" />
						</a> 
							<div class="property_details">
								<h1>
									<a href="#">${preferencia.produto.nome}</a><br/>
									<span >R$ ${preferencia.produto.preco}</span>
								</h1>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</section>
	</c:if>

	<jsp:include page="footer.jsp" />
	
</body>
</html>