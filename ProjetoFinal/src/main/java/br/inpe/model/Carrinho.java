package br.inpe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private Customer customer;
	@OneToMany
	private List<Rental> rentals = new ArrayList<Rental>();
	private double totalAmount;
	private int frequentRenterPoints;
	@OneToMany
	private List<Payment> payments = new ArrayList<Payment>();
	private double totalpayment;

	public Carrinho(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public void setFrequentRenterPoints(int frequentRenterPoints) {
		this.frequentRenterPoints = frequentRenterPoints;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public double getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(double totalpayment) {
		this.totalpayment = totalpayment;
	}

	public String statement() {
		String result = "Rental Record for " + this.customer.getName() + "\n";
		for (Rental rental : rentals) {
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(rental.getAmount()) + "\n";
		}
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "Total paid R$ " + String.valueOf(totalpayment) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
		totalAmount += rental.getAmount();
		frequentRenterPoints += rental.getFrequentRenterPoints();
	}

	public void addPayment(Payment payment) {
		payments.add(payment);
		totalpayment += payment.getValue();
		
	}

}
