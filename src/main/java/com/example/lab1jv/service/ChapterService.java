package com.example.lab1jv.service;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.Chapter;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.model.dto.ChapterDTO;
import com.example.lab1jv.repository.BookRepository;
import com.example.lab1jv.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    BookRepository bookRepository;

    public Iterable<ChapterDTO> getChapters(){
        return chapterRepository.findAll().stream()
                .map(ChapterDTO::fromChapter).collect(Collectors.toList());
    }

    public void addChapter(ChapterDTO newChapter) {
        chapterRepository.save(ChapterDTO.toChapter(newChapter, bookRepository.findById(newChapter.getBookId()).get()));
    }

    public ChapterDTO findById(Long id) {
        Chapter chapter = chapterRepository.findById(id).orElse(null);
        if(chapter == null){
            return null;
        }
        return ChapterDTO.fromChapter(chapter);
    }

    public void updateChapterById(Long id, ChapterDTO chapterDTO ) {
        Chapter repoChapter = chapterRepository.findById(id).get();
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
}
