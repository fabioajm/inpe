package br.inpe.observer;

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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.inpe.exception.NaoTemProdutoNoEstoqueException;
import br.inpe.model.Produto;
import br.inpe.observer.CarrinhoObserver;
import br.inpe.service.EstoqueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value= "/xml/cleanAll.xml")
public class TestAtualizaEstoqueObserver {
	
	private Produto produto = new Produto(1, "Filme", 250.0);
	
	@Autowired
	private EstoqueService estoqueService;
	
	private CarrinhoObserver co;
	
	@Before
	public void init(){
		co = new AtualizaEstoqueObserver(estoqueService);
	}
	
	@Test
	@DatabaseSetup("/xml/estoqueObserver.xml")
	public void atualizaEstoqueAdicaoCarrinho(){
		co.notificarAdicao(produto, 5);
		assertEquals(5, estoqueService.getQuantidade(produto));
	}
	
	@Test
	@DatabaseSetup("/xml/estoqueObserver.xml")
	public void atualizaEstoqueRemocaoCarrinho(){
		co.notificarRemocao(produto, 5);
		assertEquals(15, estoqueService.getQuantidade(produto));
	}
	
	@Test(expected = NaoTemProdutoNoEstoqueException.class)
	@DatabaseSetup("/xml/estoqueObserver.xml")
	public void adicaoMaiorQueEstoque(){
		co.notificarAdicao(produto, 15);
	}
			
}
