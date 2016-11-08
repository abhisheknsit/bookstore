package com.bookstore.services;

import java.util.List;

import com.bookstore.enums.Status;
import com.bookstore.exceptions.BookQuantityNotEnoughException;
import com.bookstore.exceptions.IsbnDoesNotExistException;
import com.bookstore.models.Book;

public interface IBookService {

	Book getBookByIsbn(Long isbn) throws IsbnDoesNotExistException;

	List<Book> getAllBooks();

	Book setStatus(Long isbn, Status deactivate) throws IsbnDoesNotExistException;

	Book createBook(Book book);

	Book changeQuantity(Long isbn, int q) throws IsbnDoesNotExistException, BookQuantityNotEnoughException;

}
