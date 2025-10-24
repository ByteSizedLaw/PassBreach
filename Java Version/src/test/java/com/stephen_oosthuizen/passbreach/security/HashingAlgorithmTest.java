/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Stephen Oosthuizen
 */
public class HashingAlgorithmTest {
    ///Targeted Test of the hash method, of class HashingAlgorithm.
    @Test
    public void testHash() {
        //Arrange
        String input = "aabc";
        String algorithm = "SHA-512";
        String expected = "f545146094761d524980e1892ec1ab6be1621aff89f1e529cb414b50c7c90e44ef06173d93ca6523fec475522be6bfae0bda9a0ee3deef70ca8317e23cb360d0";
        //Act
        String result = HashingAlgorithm.hash(input, algorithm);
        //Assert
        assertEquals(expected, result);
    }
    
}
