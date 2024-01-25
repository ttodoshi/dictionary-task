package org.example.services;

import org.example.dtos.dictionary.GetDictionaryDto;

import java.util.List;

public interface DictionaryService {
    List<GetDictionaryDto> findAllDictionaries();
}
