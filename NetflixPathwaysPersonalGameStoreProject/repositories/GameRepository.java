package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoGameRepository extends JpaRepository<Game, Integer> {
    Optional<List<Game>> findByStudio(String studio);
    Optional<List<Game>> findByEsrbRating(String esrbRating);
    Optional<List<Game>> findByTitle(String title);
}
