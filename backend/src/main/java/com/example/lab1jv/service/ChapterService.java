package com.example.lab1jv.service;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.Chapter;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.model.dto.ChapterDTO;
import com.example.lab1jv.repository.BookRepository;
import com.example.lab1jv.repository.ChapterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    BookRepository bookRepository;

    public ChapterRepository getChapterRepository() {
        return chapterRepository;
    }

    public Iterable<ChapterDTO> getChapters(){
        return chapterRepository.findAll().stream()
                .map(ChapterDTO::fromChapter).collect(Collectors.toList());
    }

    public void addChapter(ChapterDTO newChapter) {
        pagesValidation(newChapter.getNumberOfPages());
        scoreValidation(newChapter.getScoreRating());
        chapterRepository.save(ChapterDTO.toChapter(newChapter, bookRepository.findById(newChapter.getBookId()).get()));
    }

    private void pagesValidation(Integer numberOfPages) {
        if(numberOfPages < 1){
            throw new IllegalArgumentException("Number of pages must be greater than 0");
        }
    }

    private void scoreValidation(Double score){
        if(score < 0 || score > 5){
            throw new IllegalArgumentException("Score must be between 0 and 5");
        }
    }


    public ChapterDTO findById(Long id) {
        Chapter chapter = chapterRepository.findById(id).orElse(null);
        if(chapter == null){
            return null;
        }
        return ChapterDTO.fromChapter(chapter);
    }

    public void updateChapterById(Long id, ChapterDTO chapterDTO ) {
        Chapter repoChapter = chapterRepository.findById(id).orElse(null);
        if (repoChapter == null) {
            return;
        }
        pagesValidation(chapterDTO.getNumberOfPages());
        scoreValidation(chapterDTO.getScoreRating());
        Chapter chapter = ChapterDTO.toChapter(chapterDTO, bookRepository.findById(chapterDTO.getBookId()).get());
        chapter.setId(repoChapter.getId());
        chapterRepository.save(chapter);
    }

    public void deleteById(Long id) {
        chapterRepository.deleteById(id);
    }

    public BookDTO getBookByChapterId(Long id) {
        Chapter chapter = chapterRepository.findById(id).orElse(null);
        if(chapter == null){
            return null;
        }
        return BookDTO.fromBook(chapter.getBookOrigin());
    }

    public List<ChapterDTO> getChaptersByIds(List<Long> chapterIdsList) {
        return chapterRepository.findAllById(chapterIdsList).stream()
                .map(ChapterDTO::fromChapter).collect(Collectors.toList());
    }
}