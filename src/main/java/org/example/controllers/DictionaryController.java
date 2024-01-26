package org.example.controllers;

import org.example.dtos.dictionary.GetDictionaryDto;
import org.example.services.DictionaryService;

import java.util.List;

public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public List<GetDictionaryDto> findAllDictionaries() {
        return dictionaryService.findAllDictionaries();
    }
}
