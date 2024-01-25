package org.example.repositories;

import org.example.exceptions.WordAlreadyExists;
import org.example.exceptions.WordNotFoundException;
import org.example.models.Word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class WordFileRepository implements WordRepository {
    private final Path wordsFile = Paths.get("dictionaries", "words.txt");

    @Override
    public List<Word> findWordsByDictionaryName(String dictionaryName) {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(wordsFile)) {
            while (scanner.hasNext()) {
                String[] wordInformation = scanner.nextLine().split("\\|");

                if (wordInformation[0].equals(dictionaryName)) {
                    words.add(
                            new Word(
                                    wordInformation[0],
                                    wordInformation[1],
                                    wordInformation[2]
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти файл со словами");
        }
        return words;
    }

    @Override
    public Optional<Word> findWordByDictionaryNameAndWord(String dictionaryName, String word) {
        try (Scanner scanner = new Scanner(wordsFile)) {
            while (scanner.hasNext()) {
                String[] wordInformation = scanner.nextLine().split("\\|");

                if (wordInformation[0].equals(dictionaryName) && wordInformation[1].equals(word)) {
                    return Optional.of(
                            new Word(
                                    wordInformation[0],
                                    wordInformation[1],
                                    wordInformation[2]
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти файл со словами");
        }
        return Optional.empty();
    }

    @Override
    public Word save(Word word) {
        // check if word is present
        Optional<Word> searchingWord = findWordByDictionaryNameAndWord(
                word.getDictionaryName(), word.getWord()
        );
        if (searchingWord.isPresent()) {
            throw new WordAlreadyExists(word.getWord(), word.getDictionaryName());
        }

        // save word
        try {
            Files.write(wordsFile, word.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти файл со словами");
        }
        return word;
    }

    @Override
    public void deleteWordByDictionaryNameAndWord(String dictionaryName, String word) {
        try {
            List<String> allWords = Files.readAllLines(wordsFile);
            List<String> remainingLines = allWords
                    .stream()
                    .filter(line -> !(line.contains(dictionaryName) && line.contains(word)))
                    .collect(Collectors.toList());
            // check if word not found
            if (remainingLines.size() == allWords.size()) {
                throw new WordNotFoundException(
                        word, dictionaryName
                );
            }

            Files.write(wordsFile, remainingLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти файл со словами");
        }
    }
}