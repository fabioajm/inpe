package br.inpe.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.inpe.observer.AtualizaEstoqueObserver;
import br.inpe.observer.PreferenciasUsuarioObserver;
import br.inpe.service.CarrinhoComprasService;
import br.inpe.service.EstoqueService;
import br.inpe.service.ProdutoService;

@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback=false)
public class CarrinhoComprasIntegracao extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private ProdutoService  produtoService;
	
	@Autowired
	private CarrinhoComprasService ccService;
	
	@Test
	public void carrinhoComEstoqueEUsuario(){
		//cria objetos
		Produto p = new Produto();
		p.setNome("Filme");
		p.setPreco(250.0);
		produtoService.save(p);
		estoqueService.addEstoque(p, 10);
		Usuario u = new Usuario("Fabio");
		
		//cria carrinho com observers
		CarrinhoCompras cc = new CarrinhoCompras();
		cc.adicionarObserver(new AtualizaEstoqueObserver(estoqueService));
		cc.adicionarObserver(new PreferenciasUsuarioObserver(u));
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		
		ccService.save(cc);
		
		cc = ccService.find(cc.getId());
//		Hibernate.initialize(cc.getProdutos());
		
		assertTrue(u.getPreferencias().contains("Filme"));
		assertEquals(8, estoqueService.getQuantidade(p));
		assertEquals(500.0, cc.total(), 0.001);
	}
	@Test
	public void carrinhoComDoisProdutosEUsuario(){
		//cria objetos
		Produto p = new Produto();
		p.setNome("Filme");
		p.setPreco(250.0);
		produtoService.save(p);
		estoqueService.addEstoque(p, 10);
		Produto p2 = new Produto();
		p2.setNome("Livro");
		p2.setPreco(50.0);
		produtoService.save(p2);
		estoqueService.addEstoque(p2, 10);
		Usuario u = new Usuario("Fabio");
		
		//cria carrinho com observers
		CarrinhoCompras cc = new CarrinhoCompras();
		cc.adicionarObserver(new AtualizaEstoqueObserver(estoqueService));
		cc.adicionarObserver(new PreferenciasUsuarioObserver(u));
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		cc.addProduto(p2, 10);
		
		ccService.save(cc);
		
		cc = ccService.find(cc.getId());
//		Hibernate.initialize(cc.getProdutos());
		
		assertTrue(u.getPreferencias().contains("Filme"));
		assertEquals(8, estoqueService.getQuantidade(p));
		assertEquals(1000.0, cc.total(), 0.001);
	}


}
