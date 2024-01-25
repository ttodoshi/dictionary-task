package org.example.services;

import org.example.dtos.dictionary.GetDictionaryDto;
import org.example.repositories.DictionaryRepository;
import org.example.utils.mappers.DictionaryMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public List<GetDictionaryDto> findAllDictionaries() {
        return dictionaryRepository.findAll()
                .stream()
                .map(DictionaryMapper::mapDictionaryToGetDictionaryDto)
                .collect(Collectors.toList());
    }
}
