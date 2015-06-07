package br.inpe.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.inpe.observer.MockCarrinhoObserver;

public class CarrinhoComprasTest {

	private CarrinhoCompras carrinho;

	@Before
	public void init() {
		carrinho = new CarrinhoCompras();
	}

	@Test
	public void carrinhoVazio() {
		assertEquals(0.0, carrinho.total(), 0.001);
	}

	@Test
	public void adicionaUmProduto() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 1);
		assertEquals(250.0, carrinho.total(), 0.001);
		assertEquals(1, carrinho.getQuantidade(produto));
	}

	@Test
	public void adionaProdutosTiposDiferentes() {
		Produto p1 = new Produto(1, "Filme", 250.0);
		Produto p2 = new Produto(2, "Livro", 80.0);
		carrinho.addProduto(p1, 1);
		carrinho.addProduto(p2, 1);
		assertEquals(330.0, carrinho.total(), 0.001);
		assertEquals(1, carrinho.getQuantidade(p1));
	}

	@Test
	public void adicionaVariosProdutosMesmoTipo() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 4);
		assertEquals(1000.0, carrinho.total(), 0.001);
		assertEquals(4, carrinho.getQuantidade(produto));
	}

	@Test
	public void adicionaDuaVezesProdutosMesmoTipo() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 3);
		carrinho.addProduto(produto, 2);
		assertEquals(1250.0, carrinho.total(), 0.001);
		assertEquals(5, carrinho.getQuantidade(produto));
	}
	
	@Test
	public void removeProduto() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 1);
		carrinho.removeProduto(produto,1);
		assertEquals(0.0, carrinho.total(), 0.001);
		assertEquals(0, carrinho.getQuantidade(produto));
	}
	
	@Test
	public void removeQuantidadeProduto() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 5);
		carrinho.removeProduto(produto, 3);
		assertEquals(500.0, carrinho.total(), 0.001);
		assertEquals(2, carrinho.getQuantidade(produto));
	}
	@Test
	public void removeMaisQueQuantidadeProduto() {
		Produto produto = new Produto(1, "Filme", 250.0);
		carrinho.addProduto(produto, 3);
		carrinho.removeProduto(produto, 4);
		assertEquals(0.0, carrinho.total(), 0.001);
		assertEquals(0, carrinho.getQuantidade(produto));
	}
	
	@Test
	public void notificarAdicaoCarrinho(){
		Produto produto = new Produto(1, "Filme", 250.0);
		MockCarrinhoObserver co = new MockCarrinhoObserver();
		carrinho.adicionarObserver(co);
		carrinho.addProduto(produto, 3);
		co.verficarNotificacaoAdicao(produto, 3);
	}
	
	@Test
	public void notificarMaisDeUmObserver(){
		Produto produto = new Produto(1, "Filme", 250.0);
		MockCarrinhoObserver co1 = new MockCarrinhoObserver();
		MockCarrinhoObserver co2 = new MockCarrinhoObserver();
		carrinho.adicionarObserver(co1);
		carrinho.adicionarObserver(co2);
		carrinho.addProduto(produto, 3);
		co1.verficarNotificacaoAdicao(produto, 3);
		co2.verficarNotificacaoAdicao(produto, 3);
	}
	
	@Test
	public void notificarRemocaoCarrinho(){
		Produto produto = new Produto(1, "Filme", 250.0);
		MockCarrinhoObserver co = new MockCarrinhoObserver();
		carrinho.adicionarObserver(co);
		carrinho.addProduto(produto, 5);
		carrinho.removeProduto(produto, 3);
		co.verficarNotificacaoRemocao(produto, 3);
	}
	
	@Test
	public void notificarRemocaoMaiorQueOQueTem(){
		Produto produto = new Produto(1, "Filme", 250.0);
		MockCarrinhoObserver co = new MockCarrinhoObserver();
		carrinho.adicionarObserver(co);
		carrinho.addProduto(produto, 5);
		carrinho.removeProduto(produto, 8);
		co.verficarNotificacaoRemocao(produto, 5);
	}
}
