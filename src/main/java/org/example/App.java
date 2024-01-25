package org.example;

import org.example.controllers.DictionaryController;
import org.example.controllers.WordController;
import org.example.dtos.dictionary.GetDictionaryDto;
import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.GetWordDto;
import org.example.exceptions.WordAlreadyExists;
import org.example.exceptions.WordNotFoundException;
import org.example.repositories.DictionaryFileRepository;
import org.example.repositories.WordFileRepository;
import org.example.services.DictionaryServiceImpl;
import org.example.services.WordServiceImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    private static GetDictionaryDto currentDictionary;
    private static final DictionaryController dictionaryController;
    private static final WordController wordController;

    static {
        dictionaryController = new DictionaryController(
                new DictionaryServiceImpl(
                        new DictionaryFileRepository()
                )
        );
        wordController = new WordController(
                new WordServiceImpl(
                        new WordFileRepository()
                )
        );
    }

    public static void main(String[] args) {
        System.out.println("Вас приветствует лучшая в мире программа по менеджменту словарей\n");

        // commands
        try (Scanner scanner = new Scanner(System.in)) {
            choseDictionary(scanner);
            showCommands();

            while (true) {
                System.out.print("Введите команду: ");
                String command = scanner.nextLine();
                System.out.println();
                switch (command) {
                    case "help":
                        showCommands();
                        break;
                    case "1":
                        showWords();
                        break;
                    case "2":
                        findWord(scanner);
                        break;
                    case "3":
                        addWord(scanner);
                        break;
                    case "4":
                        deleteWord(scanner);
                        break;
                    case "5":
                        choseDictionary(scanner);
                        showCommands();
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println("Введите 'help' для получения списка доступных комманд");
                }
            }
        }
    }

    private static void showCommands() {
        System.out.println();
        System.out.println("Текущий словарь: " + currentDictionary.getName());
        System.out.println("Доступные действия:");
        System.out.println("1 - просмотреть все слова");
        System.out.println("2 - найти слово");
        System.out.println("3 - добавить слово");
        System.out.println("4 - удалить слово");
        System.out.println("5 - вернуться к выбору словаря");
        System.out.println("exit - выйти\n");
    }

    private static void choseDictionary(Scanner scanner) {
        System.out.println("Доступные словари:");

        List<GetDictionaryDto> dictionaries = dictionaryController.findAllDictionaries();

        for (int i = 0; i < dictionaries.size(); i++) {
            System.out.println(i + 1 + ". " + dictionaries.get(i).getName());
        }

        while (true) {
            try {
                System.out.print("Введите номер словаря: ");
                int dictNumber = Integer.parseInt(scanner.nextLine());
                currentDictionary = dictionaries.get(dictNumber - 1);
                break;
            } catch (RuntimeException e) {
                System.out.println("Такого словаря не существует");
            }
        }
    }

    private static void showWords() {
        System.out.printf("Все слова для словаря '%s':\n", currentDictionary.getName());

        wordController
                .findWordsByDictionaryName(currentDictionary.getName())
                .forEach(w ->
                        System.out.printf("%s - %s\n", w.getWord(), w.getTranslation())
                );
    }

    private static void findWord(Scanner scanner) {
        System.out.printf("Поиск по словарю '%s':\n", currentDictionary.getName());

        System.out.print("Введите слово: ");

        try {
            GetWordDto word = wordController
                    .findWordByDictionaryNameAndWord(
                            currentDictionary.getName(),
                            scanner.nextLine()
                    );

            System.out.printf("%s - %s\n", word.getWord(), word.getTranslation());
        } catch (WordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addWord(Scanner scanner) {
        System.out.printf("Добавление в словарь '%s':\n", currentDictionary.getName());

        System.out.print("Введите слово: ");
        String word = scanner.nextLine();
        System.out.print("Введите перевод: ");
        String translation = scanner.nextLine();

        try {
            GetWordDto savedWord = wordController.saveWord(
                    currentDictionary.getName(),
                    new CreateWordDto(
                            word, translation
                    )
            );

            System.out.printf("Сохранена запись: %s - %s\n", savedWord.getWord(), savedWord.getTranslation());
        } catch (WordAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteWord(Scanner scanner) {
        System.out.printf("Удаление из словаря '%s':\n", currentDictionary.getName());

        System.out.print("Введите слово: ");
        String word = scanner.nextLine();

        try {
            wordController.deleteWordByDictionaryNameAndWord(
                    currentDictionary.getName(),
                    word
            );

            System.out.println("Слово удалено");
        } catch (WordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
