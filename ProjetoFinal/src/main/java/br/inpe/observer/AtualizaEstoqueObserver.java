package br.inpe.observer;

import br.inpe.model.Produto;
import br.inpe.service.EstoqueService;

public class AtualizaEstoqueObserver implements CarrinhoObserver{
	
	private EstoqueService estoqueService;

	public AtualizaEstoqueObserver(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	@Override
	public void notificarAdicao(Produto p, int qtd) {
		estoqueService.removeEstoque(p, qtd);
	}

	@Override
	public void notificarRemocao(Produto p, int qtd) {
		estoqueService.addEstoque(p, qtd);
	}

}
