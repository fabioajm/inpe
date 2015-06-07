<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_BR">
<head>
	<title>Projeto Final</title>
	<meta charset="utf-8">
	<meta name="author" content="pixelhint.com">
	<meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/reset.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/responsive.css"/>">

	<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
</head>

<body>

	<section class="hero">
		<header>
			<div class="wrapper">
				<a href="#"><img src="<c:url value="/img/logo.png"/>" class="logo" alt="" titl=""/></a>
				<a href="#" class="hamburger"></a>
				<nav>
					<ul>
						<li><a href="#">Compre</a></li>
						<li><a href="#">Alugue</a></li>
						<li><a href="#">Doe</a></li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Contato</a></li>
					</ul>
					<a href="#" class="login_btn">Login</a>
				</nav>
			</div>
		</header><!--  end header section  -->

			<section class="caption">
				<h2 class="caption">Encontre filmes e livros</h2>
				<h3 class="properties">Filmes - Livros</h3>
			</section>
	</section><!--  end hero section  -->


<sf:form method="POST" action="save" enctype="multipart/form-data">
		
        Nome: <input type="text" name="nome"><br />
        Descrição: <textarea name="descricao" rows="4" cols="50" ></textarea><br />
		Preço: <input type="number" id="preco" pattern="\d+(\.\d{2})?" />  <br />      
        Imagem: <input type="file" name="image"><br />  <br /> 
        <input type="submit" value="Salvar">
    </sf:form>
    
    
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