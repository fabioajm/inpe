package br.inpe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPage {

	private WebDriver driver;

	public IndexPage(WebDriver driver) {
		this.driver = driver;
	}

	public IndexPage visita() {
		driver.get("http://localhost:8080/ProjetoFinal");
		return this;
	}

	public LooginPage logar() {
		WebElement btLogin = driver.findElement(By.id("login"));
		btLogin.click();
		return new LooginPage(driver);
	}
	

	public boolean usuarioLogado(String nome) {
		return driver.getPageSource().contains(nome);
	}

}
