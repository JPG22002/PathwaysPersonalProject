package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

@Controller
public class GameStoreGraphQLController {

    private final GameRepository gameRepo;
    private final ConsoleRepository consoleRepo;
    private final TshirtRepository tshirtRepo;
    private final InvoiceRepository invoiceRepo;
    private final FeeRepository feeRepo;
    private final TaxRepository taxRepo;

    public GameStoreGraphQLController(GameRepository gameRepo, ConsoleRepository consoleRepo, 
                                      TshirtRepository tshirtRepo, InvoiceRepository invoiceRepo,
                                      FeeRepository feeRepo, TaxRepository taxRepo) {
        this.gameRepo = gameRepo;
        this.consoleRepo = consoleRepo;
        this.tshirtRepo = tshirtRepo;
        this.invoiceRepo = invoiceRepo;
        this.feeRepo = feeRepo;
        this.taxRepo = taxRepo;
    }

    // General Queries

    @QueryMapping
    public Game getGame(@Argument int id) {
        return gameRepo.findById(id).orElse(null);
    }

    @QueryMapping
    public Console getConsole(@Argument int id) {
        return consoleRepo.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Game> listGames() {
        return gameRepo.findAll();
    }

    @QueryMapping
    public List<Console> listConsoles() {
        return consoleRepo.findAll();
    }

    // SchemaMappings for specific searches

    @SchemaMapping
    public List<Game> searchGamesByStudio(@Argument String studio) {
        return gameRepo.findGameByStudio(studio).orElse(null);
    }

    @SchemaMapping
    public List<Game> searchGamesByEsrbRating(@Argument String esrbRating) {
        return gameRepo.findGameByEsrbRating(esrbRating).orElse(null);
    }

    @SchemaMapping
    public List<Game> searchGamesByTitle(@Argument String title) {
        return gameRepo.findGameByTitle(title).orElse(null);
    }

    @SchemaMapping
    public List<Console> searchConsolesByManufacturer(@Argument String manufacturer) {
        return consoleRepo.findConsoleByManufacturer(manufacturer).orElse(null);
    }

}
