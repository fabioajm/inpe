package br.inpe.rules;

public abstract class RentalRule {
	
	public abstract double getAmount(int days) ;

	public int getFrequentRenterPoints(int days) {
		return 1;
	};

}
