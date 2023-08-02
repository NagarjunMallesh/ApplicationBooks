package com.project.Application.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Application.Model.Books;
import com.project.Application.Services.AppServices;


@RestController
public class BooksController {

	
	@Autowired
	AppServices service;
	
	@GetMapping("/api/testing")
	public String testing(){
		return "API testing!!!";
	}
	
	@GetMapping("/books/viewAll")
	public List<Books> getAllBooks(){
		List<Books> books = new ArrayList<>();
		books = service.getAllBooks();
		return books;
	}
	
	
	@PostMapping("/books/addBook")
	public ResponseEntity<String> insertBooks(@RequestBody Books book) {
		if(service.saveBookEntry(book)) {
			return new ResponseEntity<>("Book record added successfully!", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Book record addition failed", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/books/viewBook/{id}")
	public Books getBook(@PathVariable Long id) {
		Books book = new Books();
		book = service.getBookById(id);
		return book;
	}
	
	@DeleteMapping("/books/deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		if(service.deleteBookEntry(id)) {
			return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Record deletion failed!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/books/updateBooks/{id}")
	public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Books book){
//		Books existingBook = getBook(id);
		System.out.println("vlaue  -> " +id);
		Books existingBook = service.getBookById(id);
		System.out.println("existing ===> " + existingBook);
		
		if(existingBook == null) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
		
		existingBook.setTitle(book.getTitle());
		existingBook.setPrice(book.getPrice());
		existingBook.setGenre(book.getGenre());
		existingBook.setDate(book.getDate());
		
		service.saveBookEntry(existingBook);
		
		return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
	}
}
