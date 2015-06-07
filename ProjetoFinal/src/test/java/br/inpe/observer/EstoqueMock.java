package br.inpe.observer;

import java.util.HashMap;
import java.util.Map;

import br.inpe.model.Produto;

public class EstoqueMock {
	
	private Map<Produto, Integer> produtos = new HashMap<>();
	
	private static EstoqueMock singleton = new EstoqueMock();
	
	public static EstoqueMock getInstance(){
		return singleton;
	}
	
	private EstoqueMock(){}
	
	public void addEstoque(Produto p, int qtd){
		if(produtos.containsKey(p)){
			qtd += produtos.get(p);
		}
		produtos.put(p, qtd);
	}
	
	public void removeEstoque(Produto p, int qtd){
		int qtdCorrente = getQuantidade(p);
		if(qtdCorrente < qtd){
			throw new RuntimeException("Não tem estoque para remover");
		}else{
			produtos.put(p, qtdCorrente - qtd);
		}
	}

	public int getQuantidade(Produto p) {
		if(!produtos.containsKey(p))
			return 0;
		return produtos.get(p);
	}
	

}
