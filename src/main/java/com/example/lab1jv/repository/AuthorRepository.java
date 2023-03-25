package com.example.lab1jv.repository;

import com.example.lab1jv.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.ageYears > ?1")
    public List<Author> getAuthorsOlderThan(int age);
}

