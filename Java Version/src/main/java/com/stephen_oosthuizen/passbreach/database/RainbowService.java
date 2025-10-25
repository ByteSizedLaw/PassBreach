/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.database;

import com.stephen_oosthuizen.passbreach.controllers.ReverseEngineer;
import java.util.ArrayList;
import java.util.List;
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
        // check if the hash has already been stored - only save if not already saved
        String resultHash = searchForHash(hash);
        if(resultHash == null || resultHash.isBlank()){
            RainbowEntity e = new RainbowEntity(input, length, hash);
            repo.save(e);
        }
    }

    public RainbowService(RainbowRepository repo) {
        this.repo = repo;
    }
    
    public List<String> getEntitiesWithMaxLength() {
        List<RainbowEntity> list = repo.findAllWithMaxLength();
        List<String> result = new ArrayList<String>();
        if(list != null && !list.isEmpty()){
            for(RainbowEntity e : list){
                result.add(e.getText());
            }
            //we want to prevent ourselves from recalculating everything
            //We want to continue from where we left off in the calculation
            // since it will only reach this if the entry doesn't already exist in the DB
            ReverseEngineer.currentlength = list.get(0).getLength();
        }
        return result;
    }

    public RainbowService(){}
}
