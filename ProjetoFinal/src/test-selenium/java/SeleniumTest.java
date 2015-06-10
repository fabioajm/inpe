import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.inpe.pages.IndexPage;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
public class SeleniumTest {

	private WebDriver driver;
	private IndexPage index;
	
	@Before
	public void init(){
		driver = new FirefoxDriver();
		index = new IndexPage(driver);
	}
	
	@Test
	@DatabaseSetup("/xml/usuario.xml")
	public void cadastrarNovoUsuario(){
		index.visita().logar().cadastra("Fabio Alves","fabio_ajm@yahoo.com.br","123");
		assertTrue(index.usuarioLogado("Fabio Alves")); 
	}
	
	@Test
	@DatabaseSetup("/xml/usuario.xml")
	public void logar(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		assertTrue(index.usuarioLogado("Fabio Oliveira")); 
	}
	
	@After
	public void finaliza(){
		driver.quit();
		
	}
}
