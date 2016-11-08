package com.bookstore.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.enums.Status;
import com.bookstore.exceptions.BookQuantityNotEnoughException;
import com.bookstore.models.Book;
import com.bookstore.services.IBookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
		@Autowired
		private IBookService bookService;

		
		@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
		public ResponseEntity<Book> getBooks(@PathVariable("isbn") Long isbn) {
			Book b = bookService.getBookByIsbn(isbn);
			return new ResponseEntity<Book>(b, HttpStatus.OK);
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Book>> getBooks() {
			List<Book> bList = bookService.getAllBooks();
			return new ResponseEntity<List<Book>>(bList, HttpStatus.OK);
		}
		
		
		@RequestMapping(value = "/{isbn}/activate", method = RequestMethod.PATCH)
		public ResponseEntity<Book> activateBook(@PathVariable("isbn") Long isbn) {
			bookService.setStatus(isbn, Status.ACTIVATE);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		@RequestMapping(value = "/{isbn}/activate", method = RequestMethod.DELETE)
		public ResponseEntity<Book> deactivate(@PathVariable("isbn") Long isbn) {
			bookService.setStatus(isbn, Status.DEACTIVATE);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Book> createBook(@RequestBody Book book) {
			Book b = bookService.createBook(book);
			return new ResponseEntity<Book>(b, HttpStatus.CREATED);
		}
		
		@RequestMapping(value = "/{isbn}/quantity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Book addQuantity(@RequestBody String quantity, @PathVariable("isbn") Long isbn) {
			JSONObject object = new JSONObject(quantity);
			int q = object.getInt("quantity");
			if (q <=0 ) {
				throw new BookQuantityNotEnoughException(q);
			}
			Book b = bookService.changeQuantity(isbn, q);
			return b;
		}
		
		@RequestMapping(value = "/{isbn}/quantity", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Book deleteQuantity(@RequestBody String quantity, @PathVariable("isbn") Long isbn) {
			JSONObject object = new JSONObject(quantity);
			int q = object.getInt("quantity");
			if (q <=0 ) {
				throw new BookQuantityNotEnoughException(q);
			}
			Book b = bookService.changeQuantity(isbn, -q);
			return b;
		}
		
		@ExceptionHandler({JSONException.class, DataIntegrityViolationException.class})
		  public ResponseEntity<String> jsonParseError() {
		    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		  }
		
		@ExceptionHandler({Exception.class})
		  public ResponseEntity<String> ErrorHandling() {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
