package com.aweit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book extends RepresentationModel<Book> {

	@Id
	@Column(name = "book_id", nullable = false)
	private String bookId;
	private String description;
	@Column(name = "author_id", nullable = false)
	private String authorId;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "book_type", nullable = false)
	private String bookType;
	@Column(name = "comment")
	private String comment;

}