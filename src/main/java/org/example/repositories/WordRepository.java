package org.example.repositories;

import org.example.models.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository {
    List<Word> findWordsByDictionaryName(String dictionaryName);

    Optional<Word> findWordByDictionaryNameAndWord(String dictionaryName, String word);

    Word save(Word word);

    void deleteWordByDictionaryNameAndWord(String dictionaryName, String word);
}
