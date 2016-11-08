package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.enums.Status;
import com.bookstore.exceptions.BookQuantityNotEnoughException;
import com.bookstore.exceptions.IsbnDoesNotExistException;
import com.bookstore.models.Book;
import com.bookstore.repositories.BookRepository;

public class BookService implements IBookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book getBookByIsbn(Long isbn) throws IsbnDoesNotExistException {
		Book b = bookRepository.findByIsbn(isbn);
		if (null == b) {
			throw new IsbnDoesNotExistException(isbn);
		}
		return b;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book setStatus(Long isbn, Status deactivate) {
		Book b = bookRepository.findByIsbn(isbn);
		b.setStatus(deactivate.toString());
		bookRepository.saveAndFlush(b);
		return b;
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book changeQuantity(Long isbn, int q) throws BookQuantityNotEnoughException {
		Book b = bookRepository.findByIsbn(isbn);
		if (b.getQuantity() + q < 0) {
			throw new BookQuantityNotEnoughException(q);
		} else {
			b.setQuantity(b.getQuantity()+q);
		}
		return bookRepository.saveAndFlush(b);
	}

}
