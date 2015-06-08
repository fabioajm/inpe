package br.inpe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.inpe.enums.TypePayment;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double value;
	private TypePayment payment;

	public Payment(double value, TypePayment payment) {
		this.value = value;
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public TypePayment getPayment() {
		return payment;
	}

	public void setPayment(TypePayment payment) {
		this.payment = payment;
	}
	

}
