package org.example.exceptions;

public class WordAlreadyExists extends RuntimeException {
    public WordAlreadyExists(String word, String dictionaryName) {
        super(String.format("Слово '%s' уже существует в словаре '%s'", word, dictionaryName));
    }
}
