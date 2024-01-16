package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class VideoGameController {

    private final GameRepository gameRepository;

    public VideoGameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/list")
    public List<Game> listAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable("id") int gameId) {
        return gameRepository.findById(gameId)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestParam(required = false) String studio,
                                                  @RequestParam(required = false) String esrbRating,
                                                  @RequestParam(required = false) String title) {
        if (studio != null) {
            return gameRepository.findGameByStudio(studio)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
        } else if (esrbRating != null) {
            return gameRepository.findGameByEsrbRating(esrbRating)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
        } else if (title != null) {
            return gameRepository.findGameByTitle(title)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game) {
        Game savedGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @PutMapping
    public ResponseEntity<Void> updateGame(@Valid @RequestBody Game game) {
        gameRepository.save(game);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeGame(@PathVariable("id") int gameId) {
        gameRepository.deleteById(gameId);
        return ResponseEntity.noContent().build();
    }
}
