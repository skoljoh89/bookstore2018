package com.Palvelinohjelmointi.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Palvelinohjelmointi.bookstore.domain.Book;
import com.Palvelinohjelmointi.bookstore.domain.BookRepository;
import com.Palvelinohjelmointi.bookstore.domain.CategoryRepository;



@Controller
public class BookController {
	@Autowired
	private BookRepository brepository; 
	
	@Autowired
	private CategoryRepository crepository;
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist"; }
    
 // RESTful service to get all students
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) brepository.findAll();
    }    

	// RESTful service to get student by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findStudentRest(@PathVariable("id") Long bookId) {	
    	return brepository.findOne(bookId);
    }       
    
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("category", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/edit/{id}")
    public String addBook(@PathVariable("id") Long bookId, Model model){
    	model.addAttribute("book", brepository.findOne(bookId));
    	model.addAttribute("category", crepository.findAll());
    	/*		model.addAttribute("departments", drepository.findAll()); */
        return "editbook";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.delete(bookId);
        return "redirect:../booklist";
    }     
    
    @RequestMapping(value="/login")
   	public String login() {
   		return "login";
   	}    
}
