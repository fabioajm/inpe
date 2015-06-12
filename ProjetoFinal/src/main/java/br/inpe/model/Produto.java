package br.inpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	@Column(length=500)
	private String descricao;
	private double preco;
//	@Lob
	@Column(length=500000)//alteração para funcionar no banco H2
	private byte[] poster;

	public Produto(){}
	
	public Produto(Integer id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto(String nome, String descricao, double preco, byte[] poster) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.poster = poster;
	}
	
	public Produto(Integer id, String nome, String descricao, double preco, byte[] poster) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.poster = poster;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
