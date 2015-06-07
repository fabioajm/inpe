package br.inpe.rules;

public class RentalChildrens extends RentalRule {

	@Override
	public double getAmount(int days) {
		if (days > 3)
			return 1.5 + (days - 3) * 1.5;
		return 1.5;
	}

}
