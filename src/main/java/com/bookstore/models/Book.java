package com.bookstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="book")
	public class Book {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		private String bname;
		private Integer quantity;
		private Float price;
		private Long isbn;
		private String status;
		
		public Book() {
			
		}
		public Book(Integer id, String name) {
			this.id =id;
			this.bname = name;
		}
		
		public Long getIsbn() {
			return isbn;
		}
		public void setIsbn(Long isbn) {
			this.isbn = isbn;
		}
		public Book(String string, int i) {
			bname = string;
			quantity = i;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getBname() {
			return bname;
		}
		public void setBname(String name) {
			this.bname = name;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getStatus() {
			return status;
		}
	}

