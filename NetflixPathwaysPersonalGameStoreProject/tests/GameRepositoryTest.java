package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VideoGameRepositoryTest {

    @Autowired
    private GameRepository videoGameRepo;

    @BeforeEach
    public void setup() {
        videoGameRepo.deleteAll();
    }

    // Test for adding and retrieving a game
    @Test
    public void addAndRetrieveGameTest() {
        Game game = new Game();
        game.setTitle("Adventure Quest");
        game.setEsrbRating("E");
        game.setDescription("Epic Adventure");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("Epic Games");
        game.setQuantity(10);

        videoGameRepo.save(game);
        Optional<Game> retrievedGame = videoGameRepo.findById(game.getGameId());

        assertTrue(retrievedGame.isPresent());
        assertEquals(game, retrievedGame.get());
    }

    // Test for getting a game by ID
    @Test
    public void getGameByIdTest() {
        Game game = new Game();
        game.setTitle("Space Odyssey");
        game.setEsrbRating("T");
        game.setDescription("Space Exploration Game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Galaxy Studios");
        game.setQuantity(5);

        game = videoGameRepo.save(game);
        Optional<Game> foundGame = videoGameRepo.findById(game.getGameId());

        assertTrue(foundGame.isPresent());
        assertEquals(game, foundGame.get());
    }

    // Test for getting games by studio
    @Test
    public void getGamesByStudioTest() {
        Game game = new Game();
        game.setTitle("Mystery Island");
        game.setEsrbRating("M");
        game.setDescription("Mystery and Puzzle Solving");
        game.setPrice(new BigDecimal("39.99"));
        game.setStudio("PuzzleWorks");
        game.setQuantity(8);

        game = videoGameRepo.save(game);
        List<Game> gamesByStudio = videoGameRepo.findGameByStudio(game.getStudio()).orElse(null);

        assertNotNull(gamesByStudio);
        assertTrue(gamesByStudio.contains(game));
    }

    // Test for getting all games
    @Test
    public void getAllGamesTest() {
        Game game1 = new Game();
        game1.setTitle("Racing Mania");
        game1.setEsrbRating("E");
        game1.setDescription("High-Speed Racing Game");
        game1.setPrice(new BigDecimal("29.99"));
        game1.setStudio("FastTrack");
        game1.setQuantity(12);

        Game game2 = new Game();
        game2.setTitle("Sky High");
        game2.setEsrbRating("E");
        game2.setDescription("Flight Simulator");
        game2.setPrice(new BigDecimal("54.99"));
        game2.setStudio("SkyGames");
        game2.setQuantity(7);

        videoGameRepo.save(game1);
        videoGameRepo.save(game2);
        List<Game> allGames = videoGameRepo.findAll();

        assertEquals(2, allGames.size());
        assertTrue(allGames.contains(game1));
        assertTrue(allGames.contains(game2));
    }

    // Test for updating a game
    @Test
    public void updateGameTest() {
        Game game = new Game();
        game.setTitle("Quest for Glory");
        game.setEsrbRating("T");
        game.setDescription("Role-Playing Game");
        game.setPrice(new BigDecimal("39.99"));
        game.setStudio("RPG Inc");
        game.setQuantity(6);

        game = videoGameRepo.save(game);
        game.setDescription("New Adventures in Quest for Glory");
        game.setPrice(new BigDecimal("44.99"));

        Game updatedGame = videoGameRepo.save(game);
        Optional<Game> retrievedGame = videoGameRepo.findById(game.getGameId());

        assertTrue(retrievedGame.isPresent());
        assertEquals(updatedGame, retrievedGame.get());
    }

    // Test for deleting a game by ID
    @Test
    public void deleteGameByIdTest() {
        Game game = new Game();
        game.setTitle("Space Quest");
        game.setEsrbRating("E");
        game.setDescription("Space Adventure Game");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("CosmoFun");
        game.setQuantity(10);

        game = videoGameRepo.save(game);
        videoGameRepo.deleteById(game.getGameId());
        Optional<Game> retrievedGame = videoGameRepo.findById(game.getGameId());

        assertFalse(retrievedGame.isPresent());
    }
}
