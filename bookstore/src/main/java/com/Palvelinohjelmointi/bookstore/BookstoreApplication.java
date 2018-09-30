package com.Palvelinohjelmointi.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Palvelinohjelmointi.bookstore.domain.Book;
import com.Palvelinohjelmointi.bookstore.domain.BookRepository;
import com.Palvelinohjelmointi.bookstore.domain.Category;
import com.Palvelinohjelmointi.bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		
		@Bean
		public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
			return (args) -> {
				log.info("save a couple of books");
				
				crepository.save(new Category("Accounting"));
				crepository.save(new Category("Marketing"));
				crepository.save(new Category("Finance"));
				
				
				brepository.save(new Book("Bookname1", "Author1", crepository.findByName("Finance").get(0)));
				brepository.save(new Book("Bookname2", "Author2", crepository.findByName("Marketing").get(0)));	
				
				
				log.info("fetch all books");
				for (Book book : brepository.findAll()) {
					log.info(book.toString());
				}
		
	};
	
}
}
