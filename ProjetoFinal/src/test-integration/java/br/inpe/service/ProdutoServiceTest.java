package br.inpe.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.inpe.model.Produto;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/conf/spring.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value = "/xml/cleanAll.xml")
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private EstoqueService estoqueService;

	private Produto p;

	@Before
	public void init() {
		p = new Produto(4, "Procurando Nemo", "ação", 20.0, null);
	}

	@Test
	public void salvarSemImagem() {
		p = new Produto("Filme", "ação", 20.0, null);
		p = produtoService.salvarOuAtualizar(p, 1, null);
		assertNotNull(p.getId());
		assertEquals(1, estoqueService.getQuantidade(p));
	}

	@Test
	public void salvarComImagem() {
		byte[] imagem = { 1 };
		p = new Produto("Filme", "ação", 20.0, null);
		p = produtoService.salvarOuAtualizar(p, 1, imagem);
		assertNotNull(p.getId());
		assertEquals(1, estoqueService.getQuantidade(p));
		assertEquals(1, produtoService.findById(p.getId()).getPoster().length);
	}

	@Test
	@DatabaseSetup(value = "/xml/produtos.xml")
	public void atualizar() {
		byte[] imagem = { 1 };
		p = produtoService.salvarOuAtualizar(p, 4, imagem);
		assertEquals(4, estoqueService.getQuantidade(p));
		assertEquals(1, produtoService.findById(p.getId()).getPoster().length);
	}

	@Test
	@DatabaseSetup(value = "/xml/produtos.xml")
	public void atualizarSemPerderImagem() {
		p = produtoService.salvarOuAtualizar(p, 4, null);
		assertEquals(4, estoqueService.getQuantidade(p));
		assertTrue(produtoService.findById(p.getId()).getPoster().length > 0);
	}

	@Test
	@DatabaseSetup(value = "/xml/produtos.xml")
	public void removerProdutoSemEstoque() {
		produtoService.remover(p);
		assertEquals(0, estoqueService.getQuantidade(p));
		assertNull(produtoService.findById(p.getId()));
	}

	@Test
	@DatabaseSetup(value = "/xml/produtos.xml")
	public void removerProdutoComEstoque() {
		p = new Produto(1, "O Destino de Júpiter", "ação", 20.0, null);
		produtoService.remover(p);
		assertEquals(0, estoqueService.getQuantidade(p));
		assertNull(produtoService.findById(p.getId()));
	}
}
