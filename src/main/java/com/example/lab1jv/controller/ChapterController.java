package com.example.lab1jv.controller;

import com.example.lab1jv.model.Message;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.model.dto.ChapterDTO;
import com.example.lab1jv.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapters")
    public @ResponseBody Iterable<ChapterDTO> getAllChapters() {
        return chapterService.getChapters();
    }

    @GetMapping("/chapters/{id}")
    public @ResponseBody ResponseEntity<ChapterDTO> getChapter(@PathVariable Long id) {
        ChapterDTO chapterDTO = chapterService.findById(id);
        if(chapterDTO == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(chapterDTO, HttpStatus.OK);
        }
    }

    @GetMapping(path="/chapters/{id}/books", produces = "application/json")
    public @ResponseBody ResponseEntity<BookDTO> getBooksChapter(@PathVariable("id") Long id) {
        return new ResponseEntity<>(chapterService.getBookByChapterId(id), HttpStatus.OK);
    }

    @PostMapping("/chapters")
    public @ResponseBody ResponseEntity<Message> addChapter(@RequestBody ChapterDTO newChapter) {
        try{
            chapterService.addChapter(newChapter);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/chapters/{id}")
    public @ResponseBody ResponseEntity<Message> updateChapter(@RequestBody ChapterDTO newChapter, @PathVariable Long id) {
        try{
            chapterService.updateChapterById(id, newChapter);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/chapters/{id}")
    public @ResponseBody void deleteChapter(@PathVariable Long id) {
        chapterService.deleteById(id);
    }
}

