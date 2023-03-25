package com.example.lab1jv.service;
import com.example.lab1jv.model.Author;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.dto.AuthorDTO;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public AuthorRepository getRepo(){ return this.authorRepository; }

    public Iterable<AuthorDTO> getAuthors() {
        return authorRepository.findAll().stream().map(AuthorDTO::fromAuthor).collect(Collectors.toList());
    }

    public void addAuthor(AuthorDTO newAuthor) {
        authorRepository.save(AuthorDTO.toAuthor(newAuthor));
    }

    public AuthorDTO findById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if(author == null){
            return null;
        }
        return AuthorDTO.fromAuthor(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDTO> getAuthorsOlderThan(Integer age){
        List<Author> authors = authorRepository.findAll();
        return authors.stream().filter(author -> author.getAgeYears() > age).map(AuthorDTO::fromAuthor).collect(Collectors.toList());
    }

    public void updateAuthorWithId(Long id, AuthorDTO authorDTO ) {
        Author repoAuthor = authorRepository.findById(id).orElse(null);
        Author author = AuthorDTO.toAuthor(authorDTO);
        if (repoAuthor == null) {
            return;
        }
        author.setId(repoAuthor.getId());
        authorRepository.save(author);
    }

}

