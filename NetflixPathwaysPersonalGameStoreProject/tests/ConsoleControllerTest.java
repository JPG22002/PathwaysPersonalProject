package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
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
public class GamingConsoleControllerTest {

    @MockBean
    private ConsoleRepository consoleRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        consoleRepo.deleteAll();
    }

    // Test for creating a console
    @Test
    public void shouldCreateConsole() throws Exception {
        Console console = new Console();
        console.setModel("X-Series");
        console.setManufacturer("XYZ");
        console.setMemoryAmount("16GB");
        console.setProcessor("XYZ Processor");
        console.setPrice(new BigDecimal("539.99"));

        Mockito.when(consoleRepo.save(Mockito.any(Console.class))).thenReturn(console);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/console").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(console)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    // Test for getting a console by ID
    @Test
    public void shouldGetConsoleById() throws Exception {
        mockMvc.perform(get("/console/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test for getting consoles by manufacturer
    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        mockMvc.perform(get("/console/manufacturer").param("manufacturer", "XYZ").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test for getting all consoles
    @Test
    public void shouldGetAllConsoles() throws Exception {
        mockMvc.perform(get("/console/consoles").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test for updating a console
    @Test
    public void shouldUpdateConsole() throws Exception {
        Console console = new Console();
        console.setModel("Y-Series");
        console.setManufacturer("ABC");
        console.setMemoryAmount("32GB");
        console.setProcessor("ABC Processor");
        console.setPrice(new BigDecimal("739.99"));

        Mockito.when(consoleRepo.save(Mockito.any(Console.class))).thenReturn(console);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/console", console.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(console)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Test for deleting a console by ID
    @Test
    public void shouldRemoveConsole() throws Exception {
        mockMvc.perform(delete("/console/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
