package org.example.exceptions;

public class WordNotValidException extends RuntimeException {
    public WordNotValidException(String word, String dictionaryName) {
        super(String.format("Слово '%s' нельзя добавить в словарь '%s'", word, dictionaryName));
    }
}
