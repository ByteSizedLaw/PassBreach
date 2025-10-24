/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.ingress;

import com.stephen_oosthuizen.passbreach.controllers.GenerateHash;
import com.stephen_oosthuizen.passbreach.controllers.ReverseEngineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Stephen Oosthuizen
 */
@RestController
public class Ingress {
    
    @Autowired
    private ReverseEngineer revEng;
    @Autowired
    private GenerateHash genHash;
    
    public Ingress(ReverseEngineer revEng, GenerateHash genHash){
        this.revEng = revEng;
        this.genHash = genHash;
    }

    public Ingress(){}
    
    ///Generates a password hash based on the chosen algorithm
    @GetMapping("/generate_pword_hash")
    public String genPassword(String input, String algorithm){
        return genHash.generate(input, algorithm);
    }
    
    ///Reverse engineers the password hash - which should be impossible, but isn't
    /// @param upperLength is a customizable config to ensure that we don't exceed expected processing requirements
    /// this algorithm is very resource intensive
    @GetMapping(value = "/reverse_engineer")
    public String reverseEngineer(String hash, int length) {
        return revEng.reverseEngineer(hash, length);
    }
}
