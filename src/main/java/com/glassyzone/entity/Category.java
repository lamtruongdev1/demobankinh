package com.glassyzone.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer categoryId;

	String categoryName;

	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Định nghĩa mối quan hệ One-to-Many với Product
	List<Product> products; // Danh sách sản phẩm thuộc về Category này
}