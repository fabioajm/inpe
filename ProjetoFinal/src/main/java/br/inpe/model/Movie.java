package br.inpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.inpe.enums.TypeMovie;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	@Enumerated(EnumType.STRING)
	private TypeMovie type;
	@Column(length=500)
	private String description;
	@Lob
	@Column(length=100000)
	private byte[] poster;
	
	public Movie(){}

	public Movie(String title, TypeMovie type) {
		this.title = title;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public TypeMovie getType() {
		return type;
	}
	
	public void setType(TypeMovie type) {
		this.type = type;
	}

	public double getAmount(int days){
		return type.getRentalRule().getAmount(days);
	}

	public int getFrequentRenterPoints(int days) {
		return type.getRentalRule().getFrequentRenterPoints(days);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

}
