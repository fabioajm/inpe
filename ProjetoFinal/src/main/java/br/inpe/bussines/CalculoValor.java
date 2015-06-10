package br.inpe.bussines;

public abstract class  CalculoValor {

	public double calcular(double total) {
		return total - (total * getDesconto())/100;
	}

	public abstract double getDesconto();

}
