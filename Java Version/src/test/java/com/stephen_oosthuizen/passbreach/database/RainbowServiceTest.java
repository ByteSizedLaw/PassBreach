/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Stephen Oosthuizen
 */
@SpringBootTest
public class RainbowServiceTest {

    @Autowired
    private RainbowService rainbowService;

    @Autowired
    private RainbowRepository rainbowRepository;

    @Test
    public void endToEndTest() {
        // Arrange
        String input = "abba";
        String hash = "bb3340cfb96337e142cdd810678c0207be932bd8e6cd2890fbff2304491258efb07e6a51738ffd57dada2475b45f65650a5a2e2132a491766c8d7d7c67a9c85b";
        int length = 4;

        // Act
        rainbowService.saveHash(input, hash, length);
        String result = rainbowService.searchForHash(hash);

        // Assert
        assertEquals(input, result);
    }
}
