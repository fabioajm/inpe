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

import br.inpe.enums.TipoPagamento;
import br.inpe.pages.PagamentoPage;
import br.inpe.pages.IndexPage;
import br.inpe.pages.MeuCarrinhoPage;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value= "/xml/limparBancoSelenium.xml")
public class SeleniumTest {

	private WebDriver driver;
	private IndexPage index;
	private MeuCarrinhoPage meuCarrinho;
	private PagamentoPage pagamento;
	
	@Before
	public void init(){
		driver = new FirefoxDriver();
		index = new IndexPage(driver);
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	public void cadastrarNovoUsuario(){
		index.visita().logar().cadastra("Fabio Alves","fabio_ajm@yahoo.com.br","123");
		assertTrue(index.usuarioLogado("Fabio Alves")); 
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	public void logar(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		assertTrue(index.usuarioLogado("Fabio Oliveira")); 
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void adicionarUmProduto(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		assertTrue(index.usuarioLogado("Fabio Oliveira"));
		index.adiconarProduto(1,1);
		assertTrue(index.carrinhoContem(1));
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void adicionarDoisProdutosIguais(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		assertTrue(index.usuarioLogado("Fabio Oliveira"));
		index.adiconarProduto(1,2);
		assertTrue(index.carrinhoContem(2));
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void adicionarDoisProdutosDiferentes(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		assertTrue(index.usuarioLogado("Fabio Oliveira"));
		index.adiconarProduto(1,2);
		index.adiconarProduto(3,3);
		assertTrue(index.carrinhoContem(5));
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void adicionarProdutosEVisualizarCarrinho(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		meuCarrinho = index.adiconarProduto(2,2).adiconarProduto(4,3).meuCarrinho();
		assertTrue(meuCarrinho.valorTotal(990.0));
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void pagarComCartaoCredito(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		meuCarrinho = index.adiconarProduto(2,2).adiconarProduto(4,3).meuCarrinho();
		pagamento = meuCarrinho.pagar();
		pagamento.selecionar(TipoPagamento.CARTAO_CREDITO);
		assertTrue(pagamento.valorTotal(990.0));
	}
	
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void pagarComCartaoDebito(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		meuCarrinho = index.adiconarProduto(2,2).adiconarProduto(4,3).meuCarrinho();
		pagamento = meuCarrinho.pagar();
		pagamento.selecionar(TipoPagamento.CARTAO_DEBITO);
		assertTrue(pagamento.valorTotal(940.5));
		assertTrue(pagamento.valorDesconto(49.5));
	}
	@Test
	@DatabaseSetup("/xml/usuarioSelenium.xml")
	@DatabaseSetup("/xml/produtosSelenium.xml")
	public void pagarComBoleto(){
		index.visita().logar().efetuarLogin("fab.ajm@gmail.com","123");
		meuCarrinho = index.adiconarProduto(2,2).adiconarProduto(4,3).meuCarrinho();
		pagamento = meuCarrinho.pagar();
		pagamento.selecionar(TipoPagamento.BOLETO);
		assertTrue(pagamento.valorTotal(891.0));
		assertTrue(pagamento.valorDesconto(99.0));
	}
	
	@After
	public void finaliza(){
		driver.quit();
		
	}
}
