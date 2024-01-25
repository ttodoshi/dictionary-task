package org.example.services;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;

import java.util.List;

public interface WordService {
    List<GetWordDto> findWordsByDictionaryName(String dictionaryName);

    GetWordDto findWordByDictionaryNameAndWord(String dictionaryName, String word);

    GetWordDto saveWord(String dictionaryName, CreateWordDto createWordDto);

    void deleteWordByDictionaryNameAndWord(String dictionaryName, String word);
}
