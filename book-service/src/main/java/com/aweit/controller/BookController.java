package com.aweit.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aweit.model.Book;
import com.aweit.service.BookService;

@RestController
@RequestMapping(value="v1/author/{authorId}/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value="/{bookId}",method = RequestMethod.GET)
	public ResponseEntity<Book> getBook( @PathVariable("authorId") String authorId,
			@PathVariable("bookId") String bookId) {

		Book book = bookService.getBook(bookId, authorId, "feign");
		book.add( 
				linkTo(methodOn(BookController.class).getBook(authorId, book.getBookId())).withSelfRel(),
				linkTo(methodOn(BookController.class).createBook(book)).withRel("createBook"),
				linkTo(methodOn(BookController.class).updateBook(book)).withRel("updateBook"),
				linkTo(methodOn(BookController.class).deleteBook(book.getBookId())).withRel("deleteBook")
				);

		return ResponseEntity.ok(book);
	}

	@PutMapping
	public ResponseEntity<Book> updateBook(@RequestBody Book request) {
		return ResponseEntity.ok(bookService.updateBook(request));
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book request) {
		return ResponseEntity.ok(bookService.createBook(request));
	}

	@DeleteMapping(value="/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") String bookId) {
		return ResponseEntity.ok(bookService.deleteBook(bookId));
	}
}
