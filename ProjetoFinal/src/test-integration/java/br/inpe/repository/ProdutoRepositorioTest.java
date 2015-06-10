package br.inpe.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoRepositorioTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Test
	public void buscarApenasProdutosComEstoque(){
		fail();
	}

}
