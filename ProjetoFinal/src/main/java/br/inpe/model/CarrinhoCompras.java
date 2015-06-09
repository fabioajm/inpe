package br.inpe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.inpe.observer.CarrinhoObserver;

@Entity
public class CarrinhoCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ElementCollection
	@Column(name="quantidade")
	private Map<Produto, Integer> produtos = new HashMap<>();
	@ManyToOne
	private Usuario usuario;
	@Transient
	private List<CarrinhoObserver> observer = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public Map<Produto, Integer> getProdutos() {
		return Collections.unmodifiableMap(produtos);
	}

	public double getTotal() {
		double total = 0;
		for (Produto p : produtos.keySet()) {
			total += p.getPreco() * produtos.get(p);
		}
		return total;
	}

	public void addProduto(Produto produto, int quantidade) {
		if (produtos.containsKey(produto)) {
			quantidade += produtos.get(produto);
		}
		this.produtos.put(produto, quantidade);
		
		notificarObserversAdicao(produto, quantidade);
	}


	public int getQuantidade(Produto produto) {
		if (produtos.containsKey(produto)) {
			return produtos.get(produto);
		}
		return 0;
	}

	public void removeProduto(Produto produto, int quantidade) {
		int qtdCorrente = getQuantidade(produto) ;
		if (qtdCorrente <=  quantidade) {
			produtos.remove(produto);
			quantidade =  qtdCorrente;
		} else {
			produtos.put(produto, qtdCorrente - quantidade);
		}
		notificarObserversRemocao(produto, quantidade);
	}

	public void adicionarObserver(CarrinhoObserver co) {
		observer.add(co);
	}

	private void notificarObserversAdicao(Produto produto, int quantidade) {
		for (CarrinhoObserver carrinhoObserver : observer) {
			carrinhoObserver.notificarAdicao(produto, quantidade);
		}
	}
	
	private void notificarObserversRemocao(Produto produto, int quantidade) {
		for (CarrinhoObserver carrinhoObserver : observer) {
			carrinhoObserver.notificarRemocao(produto, quantidade);
		}
	}
	
	public int getQuantidadeProdutos() {
		return produtos.values().stream().mapToInt(value -> value ).sum();
	}

	public void esvaziar() {
		produtos.forEach((k, v) -> notificarObserversRemocao(k,v));
		produtos.clear();
	}

}
