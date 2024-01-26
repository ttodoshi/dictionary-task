package org.example.utils.validators;

import java.util.regex.Pattern;

public class DictionaryValidator {
    public boolean isWordValid(String word, String pattern) {
        Pattern r = Pattern.compile(pattern);
        return r.matcher(word).matches();
    }

    public boolean isTranslationValid(String translation) {
        return !translation.isBlank();
    }
}
