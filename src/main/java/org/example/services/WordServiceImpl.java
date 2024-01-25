package org.example.services;

import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.exceptions.WordNotFoundException;
import org.example.models.Word;
import org.example.repositories.WordRepository;
import org.example.utils.mappers.WordMapper;

import java.util.List;
import java.util.stream.Collectors;

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
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
