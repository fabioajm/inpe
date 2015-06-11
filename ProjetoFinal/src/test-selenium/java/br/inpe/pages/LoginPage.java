package br.inpe.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/ProjetoFinal/login");
	}

	public void cadastra(String nome, String email, String senha) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		WebElement campoNome = driver.findElement(By.name("nome"));
		campoNome.sendKeys(nome);

		WebElement login = driver.findElement(By.xpath("(//input[@name='login'])[2]"));
		login.sendKeys(email);

		WebElement campoSenha = driver.findElement(By.xpath("(//input[@name='senha'])[2]"));
		campoSenha.sendKeys(senha);

		WebElement salvar = driver.findElement(By.id("salvar"));
		salvar.click();
	}
	
	public void efetuarLogin(String email, String senha) {
		WebElement login = driver.findElement(By.xpath("(//input[@name='login'])[1]"));
		login.sendKeys(email);

		WebElement campoSenha = driver.findElement(By.xpath("(//input[@name='senha'])[1]"));
		campoSenha.sendKeys(senha);

		WebElement salvar = driver.findElement(By.id("enviar"));
		salvar.click();
	}
}
