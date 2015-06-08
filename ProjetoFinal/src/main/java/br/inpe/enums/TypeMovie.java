package br.inpe.enums;

import br.inpe.rules.RentalChildrens;
import br.inpe.rules.RentalNewRelease;
import br.inpe.rules.RentalRegular;
import br.inpe.rules.RentalRule;

public enum TypeMovie {
	
	REGULAR(new RentalRegular()), 
	CHILDRENS(new RentalChildrens()), 
	NEW_RELEASE(new RentalNewRelease());
	
	private RentalRule rentalRule;
	
	private TypeMovie(RentalRule rentalRule){
		this.rentalRule = rentalRule;
	}

	public RentalRule getRentalRule() {
		return rentalRule;
	}

}
