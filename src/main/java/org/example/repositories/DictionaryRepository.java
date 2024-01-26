package org.example.repositories;

import org.example.models.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository {
    List<Dictionary> findAll();

    Optional<Dictionary> findDictionaryByDictionaryName(String dictionaryName);
}
