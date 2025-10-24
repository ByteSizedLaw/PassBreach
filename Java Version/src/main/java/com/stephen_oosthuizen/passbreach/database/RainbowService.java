/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stephen Oosthuizen
 */
@Service
public class RainbowService {
    @Autowired
    private RainbowRepository repo;
    
    public String searchForHash(String hash) {
        RainbowEntity entity = repo.findByHash(hash);
        return (entity != null) ? entity.getText() : "";
    }

    
    public void saveHash(String input, String hash, int length){
        RainbowEntity e = new RainbowEntity(input, length, hash);
        repo.save(e);
    }

    public RainbowService(RainbowRepository repo) {
        this.repo = repo;
    }
    public RainbowService(){}
}
