package org.example.services;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.exceptions.WordNotFoundException;
import org.example.exceptions.WordNotValidException;
import org.example.models.Word;
import org.example.repositories.WordRepository;
import org.example.utils.mappers.WordMapper;
import org.example.utils.validators.DictionaryValidator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final Map<String, DictionaryValidator> dictionaryValidatorMap;

    public WordServiceImpl(WordRepository wordRepository, Map<String, DictionaryValidator> dictionaryValidatorMap) {
        this.wordRepository = wordRepository;
        this.dictionaryValidatorMap = dictionaryValidatorMap;
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
        DictionaryValidator dictionaryValidator = dictionaryValidatorMap.get(dictionaryName);
        if (dictionaryValidator != null && !dictionaryValidator.isWordValid(createWordDto.getWord())) {
            throw new WordNotValidException(createWordDto.getWord(), dictionaryName);
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
