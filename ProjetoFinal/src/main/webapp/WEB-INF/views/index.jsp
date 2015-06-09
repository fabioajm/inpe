<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">
<head>
	<jsp:include page="head.jsp" />
</head>
<body>

	<section class="hero">
		<header>
			<div class="wrapper">
				<a href="#"><img src="img/logo.png" class="logo" alt="" titl=""/></a>
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
						<a href="<c:url value="/carrinho/carrinho"/>" class="login_btn">Meu carrinho (${carrinho.quantidadeProdutos})</a>
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
			
				<c:forEach items="${produtos}" var="produto">
				
					<li>
							<a href="#">
								<img src="<c:url value="/produto/image?id=${produto.id}"/>" alt="" title="" class="property_img"/>
							</a>
							<span class="price">${produto.preco}</span>
							<div class="property_details">
								<h1>
									<a href="#">${produto.nome}</a>
								</h1>
								<h2>${produto.descricao } </h2>
								<a href="remove?id=${produto.id}"><h2>remover</h2></a>
								
								<form action="<c:url value="/carrinho/adicionar"/>">
									<input type="hidden" value="${produto.id}" name="id" />
									Qtd:
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

	<footer>
		<div class="wrapper footer">
			<ul>
				<li class="links">
					<ul>
						<li><a href="#">About</a></li>
						<li><a href="#">Support</a></li>
						<li><a href="#">Terms</a></li>
						<li><a href="#">Policy</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</li>

				<li class="links">
					<ul>
						<li><a href="#">Filmes</a></li>
						<li><a href="#">Livros</a></li>
						<li><a href="#">...</a></li>
					</ul>
				</li>

				<li class="links">
					<ul>
						<li><a href="#">Taubaté</a></li>
						<li><a href="#">São José dos Campos</a></li>
						<li><a href="#">...</a></li>
					</ul>
				</li>

				<li class="about">
					<p>Projeto de software utilizando TDD</p>
					<ul>
						<li><a href="http://facebook.com/pixelhint" class="facebook" target="_blank"></a></li>
						<li><a href="http://twitter.com/pixelhint" class="twitter" target="_blank"></a></li>
						<li><a href="http://plus.google.com/+Pixelhint" class="google" target="_blank"></a></li>
						<li><a href="#" class="skype"></a></li>
					</ul>
				</li>
			</ul>
		</div>

		<div class="copyrights wrapper">
			Copyright Â© 2015 <a href="http://pixelhint.com" target="_blank" class="ph_link" title="Download more free Templates">Pixelhint.com</a>. All Rights Reserved.
		</div>
	</footer><!--  end footer  -->
	
</body>
</html>