package br.inpe.bussines;

public class CalculaValorCartaoCredito extends CalculoValor {

	@Override
	public double calcular(double total) {
		return total;
	}

	@Override
	public double getDesconto() {
		// TODO Auto-generated method stub
		return 0;
	}

}
