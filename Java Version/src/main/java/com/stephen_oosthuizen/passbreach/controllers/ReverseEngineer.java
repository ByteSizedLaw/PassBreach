/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.controllers;

import com.stephen_oosthuizen.passbreach.database.RainbowEntity;
import com.stephen_oosthuizen.passbreach.database.RainbowService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stephen Oosthuizen
 */
@Service
public class ReverseEngineer {
    public static String algorithm = "SHA-512"; //todo: make this configurable
    public static String matchedPword = "";
    public static String userHash = "";
    public static int upperlength = 0;
    public static int currentlength = 0;
    public static boolean matched = false;
    @Autowired
    private RainbowService service;
    @Autowired
    private GenerateHash hasher;

    public ReverseEngineer(RainbowService service, GenerateHash hasher) {
        this.service = service;
        this.hasher = hasher;
    }
    public ReverseEngineer(){}
    
    
    public void resetMembers(){
        matchedPword = "";
        matched = false;
        currentlength = 0;
    }
    public String reverseEngineer(String hash, int upperLength){
        resetMembers();
        userHash = hash;
        upperlength = upperLength;
        
        if(alreadyInDB()){
            return matchedPword;
        }
        
        List<String> recentDbElements = getRecentSavedElements();
        System.out.println("[DEBUG] Finished searching DB");
        
        if(recentDbElements == null || recentDbElements.isEmpty()){
            System.out.println("[DEBUG] Trying to seed a new list because no entries were found");
            recentDbElements = GenerateCombinations.seed();
            handleCombinations(recentDbElements); // just a quick check if the initial seed list contains the value we're looking for
        }
        
        System.out.println("[DEBUG] Trying to Calculate the password");
        System.out.println("[DEBUG] First element: " + recentDbElements.get(0));
        if(calculate(recentDbElements) || matched){
            return matchedPword;
        }
        return "";
    }
    
    private boolean calculate(List<String> list){
        // 1. check if we've exceeded the max length
        if(currentlength>upperlength){
            return false;
        }
        // 2. check if we've found the match
        if(!matchedPword.equals("")){
            return true;
        }
        // 3. update our current length since we're now trying with a new character on the end
        currentlength = currentlength+1;
        // 4. call the generateCombinations and generatehash methods
        List<String> ls = GenerateCombinations.generate(list);
        handleCombinations(ls);
        // 5. call this method again for recursion until we find a match
        calculate(ls);
        return false;
    }
    private void handleCombinations(List<String> list){
        for(String s : list){
            String res = hasher.generate(s, algorithm);
            
            try{
                //Save this combination into our database for future lookups to be faster
                saveToDB(s, res);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            if(res.equals(userHash)){
                matchedPword = s;
                matched = true;
                return;
            }
        }
    }
    private void saveToDB(String input, String hash){
        service.saveHash(input, hash, currentlength);
    }
    private boolean alreadyInDB() {
        String result = service.searchForHash(userHash);
        if (result != null && !result.isBlank()) {
            matchedPword = result;
            System.out.println("Skipped a bunch of effort because it was already in the H2 db");
            return true;
        }
        return false;
    }
    
    private List<String> getRecentSavedElements(){
        List<String> result = service.getEntitiesWithMaxLength();
        return result;
    }
}
