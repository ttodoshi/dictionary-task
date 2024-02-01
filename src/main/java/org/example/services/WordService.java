package org.example.services;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.dtos.word.UpdateWordDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WordService {
    List<GetWordDto> findWordsByDictionaryUuidAndWord(UUID dictionaryUuid, String word);

    List<GetWordDto> findWordsByWord(String word);

    Map.Entry<String, UUID> addWordToDictionary(UUID dictionaryUuid, CreateWordDto createWordDto);

    GetWordDto updateWord(UUID dictionaryUuid, UUID wordUuid, UpdateWordDto updateWordDto);

    void deleteWordByDictionaryUuidAndWord(UUID dictionaryUuid, String word);
}
