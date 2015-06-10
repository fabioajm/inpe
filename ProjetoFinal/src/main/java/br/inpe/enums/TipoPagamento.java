package br.inpe.enums;

import br.inpe.bussines.CalculaValorBoleto;
import br.inpe.bussines.CalculaValorCartaoCredito;
import br.inpe.bussines.CalculaValorCartaoDebito;
import br.inpe.bussines.CalculoValor;

public enum TipoPagamento {
	
	CARTAO_CREDITO("Cartão de Crédito", new CalculaValorCartaoCredito()), 
	CARTAO_DEBITO("Cartão de Débito", new CalculaValorCartaoDebito()), 
	BOLETO("Boleto", new CalculaValorBoleto());

	private String valor;

	private CalculoValor calculoValor;

	TipoPagamento(String valor, CalculoValor calculoValor) {
		this.valor = valor;
		this.calculoValor = calculoValor;
	}

	public String getValor() {
		return valor;
	}

	public CalculoValor getRegra() {
		return calculoValor;
	}

}
