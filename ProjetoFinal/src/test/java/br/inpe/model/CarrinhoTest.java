package br.inpe.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.inpe.enums.TypeMovie;
import br.inpe.enums.TypePayment;

public class CarrinhoTest {
	
	private Carrinho carrinho;
	
	@Before
	public void init(){
		carrinho  = new Carrinho(new Customer("Fabio"));
	}
	
	@Test
	public void testCreationName(){
		String result = carrinho.statement();
		assertContain(result, "Rental Record for Fabio");
		
	}
	@Test
	public void testOneRegularOneDay(){
		rentMovie("Idiana Jones", TypeMovie.REGULAR ,1);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 2.0");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	@Test
	public void testOneRegularTreeDays(){
		rentMovie("Indiana Jones", TypeMovie.REGULAR, 3);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 3.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	@Test
	public void testOneChildrensOneDay(){
		rentMovie("Procurando Nemo",TypeMovie.CHILDRENS,1);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 1.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	@Test
	public void testOneChildrensFiveDays(){
		rentMovie("Procurando Nemo",TypeMovie.CHILDRENS,5);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 4.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	@Test
	public void testOneNewReleaseOneDay(){
		rentMovie("Homem Aranha 2",TypeMovie.NEW_RELEASE,1);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 3.0");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	@Test
	public void testOneNewReleaseTreeDays(){
		rentMovie("Homem Aranha 2",TypeMovie.NEW_RELEASE,3);
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 9.0");
		assertContain(result,"You earned 2 frequent renter points");
	}
	
	@Test
	public void testManyRents(){
		rentFiveMovies();
		String result = carrinho.statement();
		assertContain(result,"Amount owed is 25.0");
		assertContain(result,"You earned 8 frequent renter points");
	}

	@Test
	public void testOnePayment(){
		rentFiveMovies();
		addPayment(10.0, TypePayment.DEBIT_CARD);
		String result = carrinho.statement();
		assertContain(result, "Total paid R$ 10.0");
	}
	
	@Test
	public void testTwoPayment(){
		rentFiveMovies();
		addPayment(10.0, TypePayment.DEBIT_CARD);
		addPayment(15.0, TypePayment.CREDIT_CARD);
		String result = carrinho.statement();
		assertContain(result, "Total paid R$ 25.0");
	}
	
	private void rentFiveMovies() {
		rentMovie("Homem Aranha 2",TypeMovie.NEW_RELEASE,2);
		rentMovie("Troia",TypeMovie.NEW_RELEASE,3);
		rentMovie("Procurando Nemo",TypeMovie.CHILDRENS,3);
		rentMovie("Indiana Jones",TypeMovie.REGULAR,2);
		rentMovie("Rei Leão",TypeMovie.CHILDRENS,4);
		rentMovie("E o vento levou...",TypeMovie.REGULAR,3);
	}
	
	private void rentMovie(String title, TypeMovie type, int days) {
		Movie movie = new Movie(title, type);
		Rental rental = new Rental(movie, days);
		carrinho.addRental(rental);
	}
	
	private void addPayment(double value, TypePayment typePayment){
		Payment payment = new Payment(value, typePayment);
		carrinho.addPayment(payment);
	}

	private void assertContain(String result, String content) {
		assertTrue(result.indexOf(content)>=0);
	}

}
