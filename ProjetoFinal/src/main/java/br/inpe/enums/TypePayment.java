package br.inpe.enums;

public enum TypePayment {
	DEBIT_CARD("Debit Card"), CREDIT_CARD("Credit Card");
	
	
	private String name;

	private TypePayment(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}
