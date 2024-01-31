package org.example.repositories;

import org.example.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {
    List<Word> findWordsByDictionaryUuid(UUID dictionaryUuid);

    List<Word> findWordsByWord(String word);

    Optional<Word> findWordByDictionaryUuidAndWord(UUID dictionaryUuid, String word);

    boolean existsByDictionaryUuidAndWord(UUID dictionaryUuid, String word);

    void deleteWordByDictionaryUuidAndWord(UUID dictionaryUuid, String word);
}
