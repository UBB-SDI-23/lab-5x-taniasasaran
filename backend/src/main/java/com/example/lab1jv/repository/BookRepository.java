package com.example.lab1jv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab1jv.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
