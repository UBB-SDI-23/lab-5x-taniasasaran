package com.example.libraryApp.repository;

import com.example.libraryApp.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
