package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.dtos.word.UpdateWordDto;
import org.example.exceptions.DictionaryNotFoundException;
import org.example.exceptions.WordAlreadyExistsException;
import org.example.exceptions.WordNotFoundException;
import org.example.exceptions.WordNotValidException;
import org.example.models.Dictionary;
import org.example.models.Word;
import org.example.repositories.DictionaryRepository;
import org.example.repositories.WordRepository;
import org.example.utils.validators.DictionaryValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final DictionaryRepository dictionaryRepository;

    private final DictionaryValidator dictionaryValidator;

    private final ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<GetWordDto> findWordsByDictionaryUuidAndWord(UUID dictionaryUuid, String word) {
        dictionaryExists(dictionaryUuid);

        if (word == null) {
            return wordRepository
                    .findWordsByDictionaryUuid(dictionaryUuid)
                    .stream()
                    .map(w -> mapper.map(w, GetWordDto.class))
                    .toList();
        }
        return Collections.singletonList(
                wordRepository
                        .findWordByDictionaryUuidAndWord(dictionaryUuid, word)
                        .map(w -> mapper.map(w, GetWordDto.class))
                        .orElseThrow(() -> {
                            log.warn(String.format("word '%s' not found", word));
                            return new WordNotFoundException(
                                    word, dictionaryUuid.toString()
                            );
                        })
        );
    }

    @Override
    public List<GetWordDto> findWordsByWord(String word) {
        List<GetWordDto> words = wordRepository.findWordsByWord(word)
                .stream()
                .map(w -> mapper.map(w, GetWordDto.class))
                .toList();
        if (words.isEmpty()) {
            log.warn(String.format("word '%s' not found", word));
            throw new WordNotFoundException(word);
        }
        return words;
    }

    @Override
    @Transactional
    public Map.Entry<String, UUID> addWordToDictionary(UUID dictionaryUuid, CreateWordDto createWordDto) {
        validateWord(dictionaryUuid, createWordDto.getWord());

        return Map.entry(
                "uuid",
                wordRepository.save(
                        new Word(
                                dictionaryUuid,
                                createWordDto.getWord(),
                                createWordDto.getTranslation()
                        )
                ).getUuid()
        );
    }

    @Override
    @Transactional
    public GetWordDto updateWord(UUID dictionaryUuid, UUID wordUuid, UpdateWordDto updateWordDto) {
        validateWord(dictionaryUuid, updateWordDto.getWord());

        Word word = wordRepository.findById(wordUuid)
                .orElseThrow(() -> {
                    log.warn(String.format("word '%s' not found", wordUuid));
                    return new WordNotFoundException(wordUuid.toString());
                });
        word.setWord(updateWordDto.getWord());
        word.setTranslation(updateWordDto.getTranslation());
        return mapper.map(word, GetWordDto.class);
    }

    private void validateWord(UUID dictionaryUuid, String word) {
        Dictionary dictionary = dictionaryRepository
                .findById(dictionaryUuid)
                .orElseThrow(() -> {
                    log.warn(String.format("dictionary by uuid '%s' not found", dictionaryUuid));
                    return new DictionaryNotFoundException(
                            dictionaryUuid.toString()
                    );
                });
        if (!dictionaryValidator.isWordValid(word, dictionary.getPattern())) {
            log.warn(String.format("word '%s' not valid", word));
            throw new WordNotValidException(word, dictionaryUuid.toString());
        }
        if (wordRepository.existsByDictionaryUuidAndWord(dictionaryUuid, word)) {
            log.info(String.format("word '%s' already exists", word));
            throw new WordAlreadyExistsException(word, dictionaryUuid.toString());
        }
    }

    @Override
    @Transactional
    public void deleteWordByDictionaryUuidAndWord(UUID dictionaryUuid, String word) {
        dictionaryExists(dictionaryUuid);
        wordExists(dictionaryUuid, word);

        wordRepository
                .deleteWordByDictionaryUuidAndWord(
                        dictionaryUuid, word
                );
    }

    private void dictionaryExists(UUID dictionaryUuid) {
        if (!dictionaryRepository.existsById(dictionaryUuid)) {
            log.warn(String.format("dictionary by uuid '%s' not found", dictionaryUuid));
            throw new DictionaryNotFoundException(dictionaryUuid.toString());
        }
    }

    private void wordExists(UUID dictionaryUuid, String word) {
        if (!wordRepository.existsByDictionaryUuidAndWord(dictionaryUuid, word)) {
            log.info(String.format("word '%s' not found", word));
            throw new WordNotFoundException(word, dictionaryUuid.toString());
        }
    }
}
