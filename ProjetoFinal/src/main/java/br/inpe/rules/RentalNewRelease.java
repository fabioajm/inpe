package br.inpe.rules;


public class RentalNewRelease extends RentalRule {

	@Override
	public double getAmount(int days) {
		return days * 3;
	}

	@Override
	public int getFrequentRenterPoints(int days) {
		if (days > 1)
			return 2;
		return 1;
	}

}
