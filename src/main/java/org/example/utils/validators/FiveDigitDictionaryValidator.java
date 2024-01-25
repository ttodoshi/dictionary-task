package org.example.utils.validators;

import java.util.regex.Pattern;

public class FiveDigitDictionaryValidator implements DictionaryValidator {
    @Override
    public boolean isWordValid(String word) {
        Pattern r = Pattern.compile("^[0-9]{5}$");
        return r.matcher(word).matches();
    }

    @Override
    public String getDictionaryName() {
        return "словарь 5 цифр";
    }
}
