package org.example.services;

import org.example.dtos.dictionary.GetDictionaryDto;

import java.util.List;
import java.util.UUID;

public interface DictionaryService {
    List<GetDictionaryDto> findAllDictionaries();

    GetDictionaryDto findDictionaryByUuid(UUID dictionaryUuid);
}
