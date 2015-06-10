package br.inpe.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.inpe.enums.TipoPagamento;

public class Pagamento {

	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;
	private double valor;

	public Pagamento(TipoPagamento tipo, double valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

}
