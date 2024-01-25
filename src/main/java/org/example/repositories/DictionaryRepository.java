package org.example.repositories;

import org.example.models.Dictionary;

import java.util.List;

public interface DictionaryRepository {
    List<Dictionary> findAll();
}
