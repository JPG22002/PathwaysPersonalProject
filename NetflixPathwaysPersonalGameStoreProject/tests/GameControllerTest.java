package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoGameControllerTest {

    @MockBean
    private GameRepository gameRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize() {
        gameRepo.deleteAll();
    }

    @Test
    public void createAndRetrieveGameTest() throws Exception {
        Game game = new Game();
        game.setTitle("Legend Quest");
        game.setEsrbRating("E");
        game.setDescription("Fantasy Adventure Game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("StudioX");
        game.setQuantity(10);

        Mockito.when(gameRepo.save(Mockito.any(Game.class))).thenReturn(game);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/game").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(game)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getGameByIdTest() throws Exception {
        mockMvc.perform(get("/game/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getGamesByStudioTest() throws Exception {
        mockMvc.perform(get("/game/by-studio").param("studio", "StudioX").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getGamesByEsrbRatingTest() throws Exception {
        mockMvc.perform(get("/game/by-rating").param("esrbRating", "E").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getGamesByTitleTest() throws Exception {
        mockMvc.perform(get("/game/by-title").param("title", "Legend Quest").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllGamesTest() throws Exception {
        mockMvc.perform(get("/game/games").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateGameTest() throws Exception {
        Game game = new Game();
        game.setTitle("Legend Quest: The Awakening");
        game.setEsrbRating("T");
        game.setDescription("New Chapter of Legend Quest");
        game.setPrice(new BigDecimal("65.99"));
        game.setStudio("StudioY");
        game.setQuantity(15);

        Mockito.when(gameRepo.save(Mockito.any(Game.class))).thenReturn(game);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/game", game.getGameId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper
        .writeValueAsString(game)))
        .andDo(print())
        .andExpect(status().isNoContent());
        }
        @Test
public void deleteGameByIdTest() throws Exception {
    mockMvc.perform(delete("/game/1"))
            .andDo(print())
            .andExpect(status().isNoContent());
    }
}