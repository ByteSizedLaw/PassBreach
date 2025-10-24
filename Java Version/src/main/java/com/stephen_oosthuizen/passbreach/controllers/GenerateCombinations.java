/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen Oosthuizen
 */
public class GenerateCombinations {
    private static char[] combinations = {'a','b','c'}; 
    public static List<String> generate(List<String> list){
        List result = new ArrayList<String>();
        for(String s : list){
            for(char c : combinations){
                result.add(s+String.valueOf(c));
            }
        }
        return result;
    }
    public static List<String> seed(){
        List result = new ArrayList<String>();
        for(char c : combinations){
            result.add(String.valueOf(c));
        }
        return result;
    }
}
