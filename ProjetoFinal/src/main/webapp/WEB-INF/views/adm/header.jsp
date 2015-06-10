	<!DOCTYPE html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="hero2">
		<header>
			<div class="wrapper">
			<div align="right"  style="padding:10px">
				<c:if test="${usuarioAdm != null }">
					<label style="color:#fff;">Olá, ${usuarioAdm.nome}</label> 
				</c:if>
				</div>
				<a href="<c:url value="/adm"/>"><img src="<c:url value="/img/logo.png"/>" class="logo" alt="" title=""/></a>
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

	</section><!--  end hero section  -->