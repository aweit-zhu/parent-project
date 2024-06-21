package com.aweit.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.aweit.model.Book;
import com.aweit.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	MessageSource messages;

	@Autowired
	private BookRepository bookRepository;

	public Book getBook(String bookId, String authorId) {
		Book book = bookRepository.findByAuthorIdAndBookId(authorId, bookId);
		if (null == book) {
			throw new IllegalArgumentException(
					String.format(messages.getMessage("book.search.error.message", null, null), bookId, authorId));
		}
		return book;
	}

	public Book createBook(Book book) {
		book.setBookId(UUID.randomUUID().toString());
		bookRepository.save(book);
		return book;
	}

	public Book updateBook(Book book) {
		bookRepository.save(book);
		return book;
	}

	public String deleteBook(String bookId) {
		String responseMessage = null;
		Book book = new Book();
		book.setBookId(bookId);
		bookRepository.delete(book);
		responseMessage = String.format(messages.getMessage("book.delete.message", null, null), bookId);
		return responseMessage;

	}
}
