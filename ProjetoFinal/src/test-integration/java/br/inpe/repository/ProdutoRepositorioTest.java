package br.inpe.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.inpe.model.Produto;
import br.inpe.service.EstoqueService;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@Transactional
@DatabaseSetup(value= "/xml/cleanAll.xml")
@TransactionConfiguration(defaultRollback=false)
public class ProdutoRepositorioTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Test
	@DatabaseSetup("/xml/produtos.xml")
	public void naoDeveRetornarProduto(){
		
		List<Produto> produtos = produtoRepository.buscarProdutosEmEstoque();
		assertTrue(produtos.isEmpty());
	}

	@Test
	@DatabaseSetup("/xml/produtos.xml")
	public void deveRetornarUmProduto(){
		
		Produto p1 = produtoRepository.findById(1);
		
		estoqueService.addEstoque(p1, 3);
		
		List<Produto> produtos = produtoRepository.buscarProdutosEmEstoque();
		
		assertEquals(1, produtos.size());
		assertEquals(p1, produtos.get(0));
	}
	
	@Test
	@DatabaseSetup("/xml/produtos.xml")
	public void deveRetornarDoisProduto(){
		
		Produto p1 = produtoRepository.findById(1);
		Produto p2 = produtoRepository.findById(2);
		
		estoqueService.addEstoque(p1, 3);
		estoqueService.addEstoque(p2, 3);
		
		List<Produto> produtos = produtoRepository.buscarProdutosEmEstoque();
		
		assertEquals(2, produtos.size());
		assertTrue(produtos.contains(p1));
		assertTrue(produtos.contains(p2));
	}

}
