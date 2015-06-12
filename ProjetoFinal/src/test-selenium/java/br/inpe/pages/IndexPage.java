package br.inpe.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class IndexPage {

	private WebDriver driver;

	public IndexPage(WebDriver driver) {
		this.driver = driver;
	}

	public IndexPage visita() {
		driver.get("http://localhost:8080/ProjetoFinal");
		driver.manage().window().maximize();
		return this;
	}

	public LoginPage logar() {
		WebElement btLogin = driver.findElement(By.id("login"));
		btLogin.click();
		return new LoginPage(driver);
	}
	

	public boolean usuarioLogado(String nome) {
		return driver.getPageSource().contains(nome);
	}

	public IndexPage adiconarProduto(int indice, int qtd) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		WebElement btAdicionar = driver.findElement(By.id("adicionar"+indice));
		WebElement selectQtd = driver.findElement(By.id("qtd"+indice));
		Select realSelect = new Select(selectQtd);
		realSelect.selectByValue(""+qtd);
		btAdicionar.click();
		return this;
		
	}

	public boolean carrinhoContem(int i) {
		return driver.getPageSource().contains("Meu carrinho ("+i+")");
	}

	public MeuCarrinhoPage meuCarrinho() {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		WebElement carrinho = driver.findElement(By.id("meucarrinho"));
		carrinho.click();
		return new MeuCarrinhoPage(driver);
	}
	
}
