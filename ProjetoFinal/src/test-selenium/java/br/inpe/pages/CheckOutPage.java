package br.inpe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.inpe.enums.TipoPagamento;

public class CheckOutPage {

	private WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selecionar(TipoPagamento tipoPagamento) {
		WebElement selTipoPagamento = driver.findElement(By.name("tipo"));
		Select realSelect = new Select(selTipoPagamento);
		realSelect.selectByValue(tipoPagamento.name());
		
	}

	public boolean valorTotal(double valor) {
		return driver.getPageSource().contains("R$ " + valor);
	}

	public boolean valorDesconto(double valor) {
		return valorTotal(valor);
	}

}
