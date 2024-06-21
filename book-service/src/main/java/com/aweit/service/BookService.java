package com.aweit.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.aweit.model.Author;
import com.aweit.model.Book;
import com.aweit.repository.BookRepository;
import com.aweit.service.client.AuthorDiscoveryClient;
import com.aweit.service.client.AuthorFeignClient;
import com.aweit.service.client.AuthorRestTemplateClient;

@Service
public class BookService {

	@Autowired
	MessageSource messages;

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	AuthorFeignClient authorFeignClient;

	@Autowired
	AuthorRestTemplateClient authorRestClient;

	@Autowired
	AuthorDiscoveryClient authorDiscoveryClient;

	public Book getBook(String bookId, String authorId, String clientType) {
		Book book = bookRepo.findByAuthorIdAndBookId(authorId, bookId);
		if (null == book) {
			throw new IllegalArgumentException(
					String.format(messages.getMessage("book.search.error.message", null, null), bookId, authorId));
		}

		Author author = retrieveAuthorInfo(authorId, clientType);
		if (null != author) {
			book.setAuthorName(author.getName());
			book.setContactName(author.getContactName());
			book.setContactEmail(author.getContactEmail());
			book.setContactPhone(author.getContactPhone());
		}

		return book;
	}
	
	private Author retrieveAuthorInfo(String authorId, String clientType) {
		Author author = null;

		switch (clientType) {
		case "feign":
			System.out.println("Calling the feign client");
			author = authorFeignClient.getAuthor(authorId);
			break;
		case "rest":
			System.out.println("Calling the rest client");
			author = authorRestClient.getAuthor(authorId);
			break;
		case "discovery":
			System.out.println("Calling the discovery client");
			author = authorDiscoveryClient.getAuthor(authorId);
			break;
		default:
			author = authorRestClient.getAuthor(authorId);
			break;
		}

		return author;
	}

	public Book createBook(Book book) {
		book.setBookId(UUID.randomUUID().toString());
		bookRepo.save(book);
		return book;
	}

	public Book updateBook(Book book) {
		bookRepo.save(book);
		return book;
	}

	public String deleteBook(String bookId) {
		String responseMessage = null;
		Book book = new Book();
		book.setBookId(bookId);
		bookRepo.delete(book);
		responseMessage = String.format(messages.getMessage("book.delete.message", null, null), bookId);
		return responseMessage;

	}
}
