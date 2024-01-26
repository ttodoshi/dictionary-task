package org.example.exceptions;

public class WordAlreadyExistsException extends RuntimeException {
    public WordAlreadyExistsException(String word, String dictionaryName) {
        super(String.format("Слово '%s' уже существует в словаре '%s'", word, dictionaryName));
    }
}
