package com.glassyzone.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotBlank
	String name;

	String image;

	String description;

	@NotNull
	@PositiveOrZero
	Double price;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	Category category;

	public Integer getCategoryId() {
		return category != null ? category.getCategoryId() : null;
	}

	public void setCategoryId(Integer categoryId) {
		if (category == null) {
			category = new Category();
		}
		category.setCategoryId(categoryId);
	}

	public String getCategoryName() {
		return category != null ? category.getCategoryName() : null;
	}

	public void setCategoryName(String categoryName) {
		if (category == null) {
			category = new Category();
		}
		category.setCategoryName(categoryName);
	}
}
