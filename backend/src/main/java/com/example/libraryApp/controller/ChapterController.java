package com.example.libraryApp.controller;

import com.example.libraryApp.model.Message;
import com.example.libraryApp.model.dto.BookDTO;
import com.example.libraryApp.model.dto.ChapterDTO;
import com.example.libraryApp.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@Validated
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    public @ResponseBody List<ChapterDTO> getAllChapters() {
        return chapterService.getChapters();
    }

    @RequestMapping(value = "/chapters/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ChapterDTO> getChapter(@PathVariable Long id) {
        ChapterDTO chapterDTO = chapterService.findById(id);
        if(chapterDTO == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(chapterDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/chapters/{id}/books", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<BookDTO> getBooksChapter(@PathVariable("id") Long id) {
        return new ResponseEntity<>(chapterService.getBookByChapterId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/chapters", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Message> addChapter(@RequestBody ChapterDTO newChapter) {
        try{
            chapterService.addChapter(newChapter);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/chapters/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Message> updateChapter(@RequestBody ChapterDTO newChapter, @PathVariable Long id) {
        try{
            chapterService.updateChapterById(id, newChapter);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/chapters/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteChapter(@PathVariable Long id) {
        chapterService.deleteById(id);
    }
}

