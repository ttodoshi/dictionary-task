package org.example.exceptions;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(String word) {
        super(String.format("Слово '%s' не найдено", word));
    }

    public WordNotFoundException(String word, String dictionary) {
        super(String.format("Слово '%s' не найдено в словаре '%s'", word, dictionary));
    }
}
