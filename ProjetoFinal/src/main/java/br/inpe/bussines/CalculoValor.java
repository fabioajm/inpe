package br.inpe.bussines;

public abstract class  CalculoValor {

	public double calcular(double total) {
		return total - getDesconto(total);
	}

	public double getDesconto(double total) {
		return (total * getPorcentagem())/100;
	}
	
	public abstract double getPorcentagem();

}
