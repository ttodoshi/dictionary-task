package org.example.exceptions;

public class TranslationNotValidException extends RuntimeException {
    public TranslationNotValidException(String translation) {
        super(String.format("Перевод '%s' не является валидным", translation));
    }
}
