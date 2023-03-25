//package com.example.lab1jv;
//
//import com.example.lab1jv.model.Book;
//import com.example.lab1jv.repository.BookRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//
//@Configuration
//public class LoadDatabase {
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(BookRepository bookRepository) {
//
//        return args -> {
//            log.info("Preloading " + bookRepository.save(new Book("Bilbo Baggins", "eu", 2023, 20, 15.0, new ArrayList<>())));
//            log.info("Preloading " + bookRepository.save(new Book("Frodo Baggins", "tot eu", 2021, 50, 6.0, new ArrayList<>())));
//        };
//    }
//}
