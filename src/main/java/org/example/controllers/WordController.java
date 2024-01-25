package org.example.controllers;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.services.WordService;

import java.util.List;

public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    public List<GetWordDto> findWordsByDictionaryName(String dictionaryName) {
        return wordService.findWordsByDictionaryName(dictionaryName);
    }

    public GetWordDto findWordByDictionaryNameAndWord(String dictionaryName, String word) {
        return wordService
                .findWordByDictionaryNameAndWord(dictionaryName, word);
    }

    public GetWordDto saveWord(String dictionaryName, CreateWordDto createWordDto) {
        return wordService.saveWord(
                dictionaryName, createWordDto
        );
    }

    public void deleteWordByDictionaryNameAndWord(String dictionaryName, String word) {
        wordService.deleteWordByDictionaryNameAndWord(
                dictionaryName, word
        );
    }
}
