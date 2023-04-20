package com.example.libraryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryApp.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
