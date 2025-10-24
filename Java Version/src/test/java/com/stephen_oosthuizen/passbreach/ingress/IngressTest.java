/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.ingress;

import com.stephen_oosthuizen.passbreach.controllers.GenerateHash;
import com.stephen_oosthuizen.passbreach.controllers.ReverseEngineer;
import com.stephen_oosthuizen.passbreach.database.RainbowRepository;
import com.stephen_oosthuizen.passbreach.database.RainbowService;
import com.stephen_oosthuizen.passbreach.security.HashingAlgorithm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 *
 * @author Stephen Oosthuizen
 */
public class IngressTest {

    /// Test of the generate_pword_hash ingress point
    /// Should return a successful hash
    /// This test does an end-to-end test for the hashing algorithm
    @Test
    public void testGenPassword() {
        //Arrange
        String input = "aabc";
        String algorithm = "SHA-512";
        String expected = "f545146094761d524980e1892ec1ab6be1621aff89f1e529cb414b50c7c90e44ef06173d93ca6523fec475522be6bfae0bda9a0ee3deef70ca8317e23cb360d0";
        Ingress ingress = new Ingress();
        //Act
        String result = ingress.genPassword(input, algorithm);
        //Assert
        assertEquals(result, expected);
    }

    /**
     * Test of reverseEngineer method, of class Ingress.
     */
    
    @Test
    public void testReverseEngineer(){
        // Arrange
        String expected = "aabc";
        String algorithm = "SHA-512";
        String hashed = "f545146094761d524980e1892ec1ab6be1621aff89f1e529cb414b50c7c90e44ef06173d93ca6523fec475522be6bfae0bda9a0ee3deef70ca8317e23cb360d0";
        
        RainbowService rbServ = mock(RainbowService.class);
        when(rbServ.searchForHash(anyString())).thenReturn("");
        doNothing().when(rbServ).saveHash(anyString(), anyString(), anyInt());
        
        HashingAlgorithm alg = new HashingAlgorithm();
        GenerateHash hash = new GenerateHash();
        ReverseEngineer rev = new ReverseEngineer(rbServ, hash);
        Ingress ingressPoint = new Ingress(rev, hash);
        // Act
        String result = ingressPoint.reverseEngineer(hashed, 4);
        
        // Assert
        assertEquals(expected, result);
    }
    
}
