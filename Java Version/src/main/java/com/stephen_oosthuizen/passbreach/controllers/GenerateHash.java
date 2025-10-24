/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.controllers;

import com.stephen_oosthuizen.passbreach.security.HashingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stephen Oosthuizen
 */
@Service
public class GenerateHash {
    @Autowired
    private HashingAlgorithm hasher;
    public GenerateHash(HashingAlgorithm hasher) {
        this.hasher = hasher;
    }

    public GenerateHash() {
    }
    
    public String generate(String input, String algorithm){
        return hasher.hash(input, algorithm);
    }
}
