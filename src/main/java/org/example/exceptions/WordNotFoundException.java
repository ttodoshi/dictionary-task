package org.example.exceptions;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(String word, String dictionaryName) {
        super(String.format("Слово '%s' не найдено в словаре '%s'", word, dictionaryName));
    }
}
