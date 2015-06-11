package br.inpe.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.inpe.exception.NaoTemProdutoNoEstoqueException;
import br.inpe.model.Produto;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
public class EstoqueServiceTest {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Test
	@DatabaseSetup("/xml/estoque.xml")
	public void adionarUmProdutoEstoque(){
		Produto p = new Produto(1, "Filme", 250.0);
		estoqueService.addEstoque(p, 1);
		assertEquals(1, estoqueService.getQuantidade(p));
	}
	
	@Test
	@DatabaseSetup("/xml/estoque.xml")
	public void adicionaProdutosTiposDiferentes(){
		Produto p1 = new Produto(1, "Filme", 250.0);
		Produto p2 = new Produto(2, "Celular", 500.0);
		estoqueService.addEstoque(p1, 10);
		estoqueService.addEstoque(p2, 5);
		assertEquals(10, estoqueService.getQuantidade(p1));
		assertEquals(5, estoqueService.getQuantidade(p2));
	}

	@Test
	@DatabaseSetup("/xml/estoque.xml")
	public void adicionaDuasVezesProdutosMesmoTipo(){
		Produto p = new Produto(1, "Filme", 250.0);
		estoqueService.addEstoque(p, 10);
		estoqueService.addEstoque(p, 5);
		assertEquals(15, estoqueService.getQuantidade(p));
	}
	
	@Test
	@DatabaseSetup("/xml/estoque.xml")
	@DatabaseTearDown("/xml/estoque.xml")
	public void removeProdutoEstoque(){
		Produto p = new Produto(1, "Filme", 250.0);
		estoqueService.addEstoque(p, 1);
		assertEquals(1, estoqueService.getQuantidade(p));
		
		estoqueService.removeEstoque(p, 1);
		assertEquals(0, estoqueService.getQuantidade(p));
	}
	
	@Test
	@DatabaseSetup("/xml/estoque.xml")
	public void removeQuantidadeProduto(){
		Produto p = new Produto(1, "Filme", 250.0);
		estoqueService.addEstoque(p, 10);
		assertEquals(10, estoqueService.getQuantidade(p));
		
		estoqueService.removeEstoque(p, 5);
		assertEquals(5, estoqueService.getQuantidade(p));
	}

	@Test(expected=NaoTemProdutoNoEstoqueException.class)
	@DatabaseSetup("/xml/estoque.xml")
	public void removeMaisQueQuantidadeProduto(){
		Produto p = new Produto(1, "Filme", 250.0);
		estoqueService.addEstoque(p, 10);
		assertEquals(10, estoqueService.getQuantidade(p));
		
		estoqueService.removeEstoque(p, 15);
	}

}
