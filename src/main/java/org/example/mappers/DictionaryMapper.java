package org.example.mappers;

import org.example.dtos.dictionary.GetDictionaryDto;
import org.example.models.Dictionary;

public class DictionaryMapper {
    public static GetDictionaryDto mapDictionaryToGetDictionaryDto(Dictionary dictionary) {
        return new GetDictionaryDto(
                dictionary.getName()
        );
    }
}
