package com.example.lab1jv.repository;

import com.example.lab1jv.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
