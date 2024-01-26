package org.example.repositories;

import org.example.models.Dictionary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DictionaryFileRepository implements DictionaryRepository {
    private final Path dictionariesFile = Paths.get("dictionaries", "dicts.txt");

    @Override
    public List<Dictionary> findAll() {
        List<Dictionary> dictionaries = new ArrayList<>();
        try (Scanner scanner = new Scanner(dictionariesFile)) {
            while (scanner.hasNext()) {
                String[] dictionaryInformation = scanner.nextLine().split("\\|");

                dictionaries.add(
                        new Dictionary(
                                dictionaryInformation[0],
                                dictionaryInformation[1]
                        )
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти словари");
        }
        return dictionaries;
    }

    @Override
    public Optional<Dictionary> findDictionaryByDictionaryName(String dictionaryName) {
        try (Scanner scanner = new Scanner(dictionariesFile)) {
            while (scanner.hasNext()) {
                String[] dictionaryInformation = scanner.nextLine().split("\\|");

                if (dictionaryInformation[0].equals(dictionaryName)) {
                    return Optional.of(
                            new Dictionary(
                                    dictionaryInformation[0],
                                    dictionaryInformation[1]
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти словари");
        }
        return Optional.empty();
    }
}
