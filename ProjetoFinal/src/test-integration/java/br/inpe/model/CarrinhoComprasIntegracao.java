package br.inpe.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.inpe.observer.AtualizaEstoqueObserver;
import br.inpe.observer.PreferenciasUsuarioObserver;
import br.inpe.service.CarrinhoComprasService;
import br.inpe.service.EstoqueService;
import br.inpe.service.ProdutoService;
import br.inpe.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback=false)
@DatabaseSetup(value= "/xml/cleanAll.xml")
public class CarrinhoComprasIntegracao extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private ProdutoService  produtoService;
	
	@Autowired
	private CarrinhoComprasService ccService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@DatabaseSetup("/xml/carrinhoCompraIntegracao.xml")
	public void carrinhoComProdutoEUsuario(){
		//cria objetos
		Produto p = produtoService.findById(1);
		Usuario u = usuarioService.buscarPorLogin("fabio@gmail.com");
		
		//cria carrinho com observers
		CarrinhoCompras cc = ccService.criarCarrinho(u);
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		
		ccService.save(cc);
		
		cc = ccService.find(cc.getId());
//		Hibernate.initialize(cc.getProdutos());
		
		assertEquals(1, u.getPreferencias().size());
		assertEquals(8, estoqueService.getQuantidade(p));
		assertEquals(500.0, cc.getTotal(), 0.001);
	}
	@Test
	@DatabaseSetup("/xml/carrinhoCompraIntegracao.xml")
	public void carrinhoComDoisProdutosEUsuario(){
		//cria objetos
		Produto p = produtoService.findById(1);
		Produto p2 = produtoService.findById(2);
		Usuario u = usuarioService.buscarPorLogin("fabio@gmail.com");
		
		//cria carrinho com observers
		CarrinhoCompras cc = ccService.criarCarrinho(u);
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		cc.addProduto(p2, 10);
		
		ccService.save(cc);
		
		cc = ccService.find(cc.getId());
		
		assertEquals(2,u.getPreferencias().size());
		assertEquals(8, estoqueService.getQuantidade(p));
		assertEquals(1000.0, cc.getTotal(), 0.001);
	}


}
