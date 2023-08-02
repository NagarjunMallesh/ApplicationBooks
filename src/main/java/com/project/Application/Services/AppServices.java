package com.project.Application.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Application.Model.Books;
import com.project.Application.Repository.AppRepository;

@Service
public class AppServices {

	@Autowired
	AppRepository repo;
	
	
	public List<Books> getAllBooks(){
		ArrayList<Books> books = new ArrayList<>();
		repo.findAll().forEach(book -> books.add(book));
		
		return books;
	}
	
	
	public Books getBookById(long Id) {
		return repo.findById(Id).get();
	}
	
	public boolean saveBookEntry(Books books) {
		Books add = repo.save(books);
		
		if(getBookById(add.getId())!= null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteBookEntry(long id) {
		repo.deleteById(id);
		
		if(repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
	
}
