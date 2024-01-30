package org.example.mappers;

import org.example.dtos.word.GetWordDto;
import org.example.models.Word;

public class WordMapper {
    public static GetWordDto mapWordToGetWordDto(Word word) {
        return new GetWordDto(
                word.getDictionaryName(),
                word.getWord(),
                word.getTranslation()
        );
    }
}
