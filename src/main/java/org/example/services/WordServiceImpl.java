package org.example.services;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.exceptions.DictionaryNotFoundException;
import org.example.exceptions.TranslationNotValidException;
import org.example.exceptions.WordNotFoundException;
import org.example.exceptions.WordNotValidException;
import org.example.models.Dictionary;
import org.example.models.Word;
import org.example.repositories.DictionaryRepository;
import org.example.repositories.WordRepository;
import org.example.mappers.WordMapper;
import org.example.utils.validators.DictionaryValidator;

import java.util.List;
import java.util.stream.Collectors;

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryValidator dictionaryValidator;

    public WordServiceImpl(WordRepository wordRepository, DictionaryRepository dictionaryRepository, DictionaryValidator dictionaryValidator) {
        this.wordRepository = wordRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryValidator = dictionaryValidator;
    }

    @Override
    public List<GetWordDto> findWordsByDictionaryName(String dictionaryName) {
        return wordRepository.findWordsByDictionaryName(dictionaryName)
                .stream()
                .map(WordMapper::mapWordToGetWordDto)
                .collect(Collectors.toList());
    }

    @Override
    public GetWordDto findWordByDictionaryNameAndWord(String dictionaryName, String word) {
        return wordRepository.findWordByDictionaryNameAndWord(
                        dictionaryName, word
                ).map(WordMapper::mapWordToGetWordDto)
                .orElseThrow(() -> new WordNotFoundException(
                        word, dictionaryName
                ));
    }

    @Override
    public GetWordDto saveWord(String dictionaryName, CreateWordDto createWordDto) {
        // word valid for this dictionary
        Dictionary dictionary = dictionaryRepository
                .findDictionaryByDictionaryName(dictionaryName)
                .orElseThrow(() -> new DictionaryNotFoundException(
                        dictionaryName
                ));
        if (!dictionaryValidator.isWordValid(createWordDto.getWord(), dictionary.getPattern())) {
            throw new WordNotValidException(createWordDto.getWord(), dictionaryName);
        }
        if (!dictionaryValidator.isTranslationValid(createWordDto.getTranslation())) {
            throw new TranslationNotValidException(createWordDto.getTranslation());
        }

        return WordMapper.mapWordToGetWordDto(
                wordRepository.save(
                        new Word(
                                dictionaryName,
                                createWordDto.getWord(),
                                createWordDto.getTranslation()
                        )
                )
        );
    }

    @Override
    public void deleteWordByDictionaryNameAndWord(String dictionaryName, String word) {
        wordRepository.deleteWordByDictionaryNameAndWord(
                dictionaryName, word
        );
    }
}
