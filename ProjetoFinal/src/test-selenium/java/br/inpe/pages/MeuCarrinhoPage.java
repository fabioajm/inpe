package br.inpe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MeuCarrinhoPage {

	private WebDriver driver;

	public MeuCarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean valorTotal(double valor) {
		return driver.getPageSource().contains("R$ " + valor);
	}

	public PagamentoPage pagar() {
		WebElement checkout = driver.findElement(By.className("pagar_btn"));
		checkout.click();
		return new PagamentoPage(driver);
	}

}
