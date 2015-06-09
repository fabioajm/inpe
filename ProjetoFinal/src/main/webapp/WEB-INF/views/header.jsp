	<!DOCTYPE html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<section class="hero2">
		<header>
			<div class="wrapper">
			<div align="right"  style="padding:10px">
				<c:if test="${usuario != null }">
					<label style="color:#fff;">Olá, ${usuario.nome}</label> 
				</c:if>
				</div>
				<a href="<c:url value="/index"/>"><img src="<c:url value="/img/logo.png"/>" class="logo" alt="" title=""/></a>
				<a href="#" class="hamburger"></a>
				<nav>
					<ul>
						<li><a href="<c:url value="/index#listings"/>">Compre</a></li>
						<li><a href="#">Alugue</a></li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Contato</a></li>
					</ul>
					<c:if test="${usuario == null }">
						<a href="<c:url value="/login"/>" class="login_btn">Login</a>
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
	</section><!--  end hero section  -->
