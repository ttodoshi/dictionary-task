package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.dictionary.GetDictionaryDto;
import org.example.exceptions.DictionaryNotFoundException;
import org.example.repositories.DictionaryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<GetDictionaryDto> findAllDictionaries() {
        return dictionaryRepository.findAll()
                .stream()
                .map(d -> mapper.map(d, GetDictionaryDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GetDictionaryDto findDictionaryByUuid(UUID dictionaryUuid) {
        return dictionaryRepository.findById(dictionaryUuid)
                .map(d -> mapper.map(d, GetDictionaryDto.class))
                .orElseThrow(() -> {
                    log.warn(String.format("dictionary by uuid '%s' not found", dictionaryUuid));
                    return new DictionaryNotFoundException(dictionaryUuid.toString());
                });
    }
}
