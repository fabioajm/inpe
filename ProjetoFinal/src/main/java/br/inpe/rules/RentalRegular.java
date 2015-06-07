package br.inpe.rules;

public class RentalRegular extends RentalRule{

	@Override
	public double getAmount(int days) {
		if (days > 2)
			return 2 + (days - 2) * 1.5;
		return 2;
	}

}
